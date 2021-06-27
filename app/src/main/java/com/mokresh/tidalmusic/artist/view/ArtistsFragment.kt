package com.mokresh.tidalmusic.artist.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.artist.data.ArtistsViewModel
import com.mokresh.tidalmusic.artist.models.ArtistsData
import com.mokresh.tidalmusic.base.BaseFragment
import com.mokresh.tidalmusic.base.OnClickListener
import com.mokresh.tidalmusic.base.PagingLoadStateAdapter
import com.mokresh.tidalmusic.databinding.FragmentArtistsBinding
import com.mokresh.tidalmusic.utils.DebouncingQueryTextListener
import kotlinx.coroutines.flow.collectLatest


class ArtistsFragment : BaseFragment<FragmentArtistsBinding, ArtistsViewModel>
    (R.layout.fragment_artists, ArtistsViewModel::class), OnClickListener<ArtistsData> {

    private val artistsAdapter = ArtistsAdapter(this)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchView.setOnQueryTextListener(
            DebouncingQueryTextListener(
                this.lifecycle
            ) { newText ->
                newText?.let {
                    if (newText.isNotEmpty()) {
                        newText.let { viewModel.getArtists(it) }
                        initArtistsRecyclerView()

                    }
                }
            }
        )

        viewModel.progressLiveEvent.observe(this, Observer {
            Log.e("progres",it.toString())
        })

        viewModel.errorMessage.observe(this, Observer {
            Log.e("progres",it.toString())
        })


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

    override fun onItemClick(item: ArtistsData) {
        val directions = item.name?.let { ArtistsFragmentDirections.actionArtistsFragmentToAlbumsFragment(it) }
        directions?.let { findNavController().navigate(it) }

    }
}