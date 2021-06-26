package com.mokresh.tidalmusic

import android.os.Bundle
import androidx.paging.LoadState
import com.mokresh.tidalmusic.artist.ArtistsAdapter
import com.mokresh.tidalmusic.artist.ArtistsViewModel
import com.mokresh.tidalmusic.base.BaseActivity
import com.mokresh.tidalmusic.base.PagingLoadStateAdapter
import com.mokresh.tidalmusic.databinding.ActivityMainBinding
import com.mokresh.tidalmusic.utils.DebouncingQueryTextListener
import kotlinx.coroutines.flow.collectLatest


class MainActivity : BaseActivity<ActivityMainBinding,
        ArtistsViewModel>(R.layout.activity_main, ArtistsViewModel::class) {
    private val artistsAdapter = ArtistsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.searchView.setOnQueryTextListener(
            DebouncingQueryTextListener(
                this@MainActivity.lifecycle
            ) { newText ->
                newText?.let {
                    if (newText.isNotEmpty()) {
                        newText.let { viewModel.getArtists(it) }
                        initArtistsRecyclerView()

                    }
                }
            }
        )
    }

    private fun initArtistsRecyclerView() {
        with(artistsAdapter) {
            binding.swipeRefresh.setOnRefreshListener { refresh() }
            binding.rvArtists.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )
            with(viewModel) {
                launchOnLifecycleScope {
                    artistsFlow?.collectLatest { submitData(it) }
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