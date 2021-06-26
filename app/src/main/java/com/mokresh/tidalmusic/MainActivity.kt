package com.mokresh.tidalmusic

import android.os.Bundle
import androidx.paging.LoadState
import com.mokresh.tidalmusic.artist.ArtistsAdapter
import com.mokresh.tidalmusic.artist.ArtistsViewModel
import com.mokresh.tidalmusic.base.BaseActivity
import com.mokresh.tidalmusic.base.PagingLoadStateAdapter
import com.mokresh.tidalmusic.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

class MainActivity : BaseActivity<ActivityMainBinding,
        ArtistsViewModel>(R.layout.activity_main, ArtistsViewModel::class) {
    lateinit var artistsAdapter: ArtistsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        artistsAdapter = ArtistsAdapter()

        viewModel.getArtists("emin")
        with(artistsAdapter) {
            binding.swipeRefresh.setOnRefreshListener { refresh() }
            binding.rvArtists.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )
            with(viewModel) {
                launchOnLifecycleScope {
                    artistsFlow.collectLatest { submitData(it) }
                }
                launchOnLifecycleScope {
                    loadStateFlow.collectLatest {
                        binding.swipeRefresh.isRefreshing = it.refresh is LoadState.Loading
                    }
                }

            }
        }


    }


}