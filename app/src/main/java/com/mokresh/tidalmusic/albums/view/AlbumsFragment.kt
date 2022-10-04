package com.mokresh.tidalmusic.albums.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.albums.data.AlbumsViewModel
import com.mokresh.tidalmusic.base.BaseFragment
import com.mokresh.tidalmusic.base.PagingLoadStateAdapter
import com.mokresh.tidalmusic.databinding.FragmentAlbumsBinding
import com.mokresh.tidalmusic.utils.Constants
import com.mokresh.tidalmusic.utils.UIEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map


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
        navController.previousBackStackEntry?.savedStateHandle?.set(
            Constants.IS_FROM_POP_STACK,
            true
        )
    }

    private fun initAlbumsRecyclerView() {
        with(albumsAdapter) {
            binding.swipeRefresh.setOnRefreshListener { refresh() }
            binding.rvAlbums.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )
            launchOnLifecycleScope {
                loadStateFlow.collectLatest {
                    viewModel.isDataEmpty.set(false)
                    val error = when {
                        it.prepend is LoadState.Error -> it.prepend as LoadState.Error
                        it.append is LoadState.Error -> it.append as LoadState.Error
                        it.refresh is LoadState.Error -> it.refresh as LoadState.Error
                        else -> null
                    }
                    if (!error?.error?.message.isNullOrEmpty())
                        viewModel.errorMessage.value = error?.error?.message

                    binding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                }

            }
            launchOnLifecycleScope {
                loadStateFlow.map { it.refresh }
                    .distinctUntilChanged().collect {
                        if (it is LoadState.NotLoading) {
                            if (itemCount == 0) {
                                viewModel.isDataEmpty.set(true)
                            } else {
                                viewModel.isDataEmpty.set(false)
                            }
                        }
                    }

            }

        }


    }

    override fun onUIEventTriggered(event: UIEvent) {
        when (event) {
            is UIEvent.NavigateToTracks -> {
                val directions =
                    event.albumsData.let {
                        AlbumsFragmentDirections.actionAlbumsFragmentToTracksFragment(
                            it
                        )
                    }
                directions.let { findNavController().navigate(it) }

            }
            is UIEvent.RenderAlbumsList -> {
                launchOnLifecycleScope {
                    albumsAdapter.submitData(event.albumsData)
                }
            }

            is UIEvent.UpdateIsFavouriteAlbum -> {
                if (event.isFavourite) {
                    viewModel.insertFavourite(event.albumsData)
                } else {
                    viewModel.deleteFavourite(event.albumsData.id)
                }
            }
        }
    }


}