package com.mokresh.tidalmusic.albums

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.albums.model.AlbumsData
import com.mokresh.tidalmusic.artist.view.ArtistsFragmentDirections
import com.mokresh.tidalmusic.base.BaseFragment
import com.mokresh.tidalmusic.base.OnClickListener
import com.mokresh.tidalmusic.base.PagingLoadStateAdapter
import com.mokresh.tidalmusic.databinding.FragmentAlbumsBinding
import com.mokresh.tidalmusic.utils.Constants
import com.mokresh.tidalmusic.utils.UIEvent
import kotlinx.coroutines.flow.collectLatest


class AlbumsFragment : BaseFragment<FragmentAlbumsBinding, AlbumsViewModel>
    (R.layout.fragment_albums, AlbumsViewModel::class) {
    private val albumsAdapter = AlbumsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            val query: String = AlbumsFragmentArgs.fromBundle(requireArguments()).query
            viewModel.getAlbums(query)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        initAlbumsRecyclerView()

        navController.previousBackStackEntry?.savedStateHandle?.set(Constants.IS_FROM_POP_STACK, true)
    }

    private fun initAlbumsRecyclerView() {
        with(albumsAdapter) {
            binding.swipeRefresh.setOnRefreshListener { refresh() }
            binding.rvAlbums.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )
            with(viewModel) {
                launchOnLifecycleScope {
                    albumsFlow?.collectLatest { submitData(it) }
                }
                launchOnLifecycleScope {
                    loadStateFlow.collectLatest {
                        binding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                    }
                }

            }
        }


    }

    override fun onUIEventTriggered(event: UIEvent) {
        when (event) {
            is UIEvent.NavigateToTracks -> {
                val directions =
                    event.albumsData.let { AlbumsFragmentDirections.actionAlbumsFragmentToTracksFragment(it) }
                directions?.let { findNavController().navigate(it) }

            }
        }
    }


}