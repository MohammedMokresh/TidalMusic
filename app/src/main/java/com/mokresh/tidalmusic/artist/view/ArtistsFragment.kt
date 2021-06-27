package com.mokresh.tidalmusic.artist.view

import android.os.Bundle
import android.view.View
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
import com.mokresh.tidalmusic.utils.UIEvent
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
//        artistsAdapter.addLoadStateListener { loadState ->
//
//
//            if (loadState.refresh is LoadState.Loading){
////                progressBar.visibility = View.VISIBLE
//            }
//            else{
////                progressBar.visibility = View.GONE
//
//                // getting the error
//                val error = when {
//                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
//                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
//                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
//                    else -> null
//                }
//                error?.let {
//                    Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG).show()
//                }
//            }
//        }

    }

    override fun onItemClick(item: ArtistsData) {
        val directions = item.name?.let { ArtistsFragmentDirections.actionArtistsFragmentToAlbumsFragment(it) }
        directions?.let { findNavController().navigate(it) }

    }

    override fun onUIEventTriggered(event: UIEvent) {
        TODO("Not yet implemented")
    }
}