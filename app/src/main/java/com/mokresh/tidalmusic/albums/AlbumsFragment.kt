package com.mokresh.tidalmusic.albums

import android.os.Bundle
import android.view.View
import androidx.paging.LoadState
import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.albums.model.AlbumsData
import com.mokresh.tidalmusic.base.BaseFragment
import com.mokresh.tidalmusic.base.OnClickListener
import com.mokresh.tidalmusic.base.PagingLoadStateAdapter
import com.mokresh.tidalmusic.databinding.FragmentAlbumsBinding
import com.mokresh.tidalmusic.utils.UIEvent
import kotlinx.coroutines.flow.collectLatest


class AlbumsFragment : BaseFragment<FragmentAlbumsBinding, AlbumsViewModel>
    (R.layout.fragment_albums, AlbumsViewModel::class), OnClickListener<AlbumsData> {
    private val albumsAdapter = AlbumsAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val query: String = AlbumsFragmentArgs.fromBundle(requireArguments()).query
            viewModel.getAlbums(query)
            initAlbumsRecyclerView()

        }

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

    override fun onItemClick(item: AlbumsData) {


    }

    override fun onUIEventTriggered(event: UIEvent) {
        TODO("Not yet implemented")
    }


}