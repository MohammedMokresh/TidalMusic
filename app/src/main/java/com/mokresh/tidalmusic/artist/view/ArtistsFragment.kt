package com.mokresh.tidalmusic.artist.view

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.artist.data.ArtistsViewModel
import com.mokresh.tidalmusic.base.BaseFragment
import com.mokresh.tidalmusic.base.PagingLoadStateAdapter
import com.mokresh.tidalmusic.databinding.FragmentArtistsBinding
import com.mokresh.tidalmusic.utils.Constants.IS_FROM_POP_STACK
import com.mokresh.tidalmusic.utils.DebouncingQueryTextListener
import com.mokresh.tidalmusic.utils.UIEvent
import kotlinx.coroutines.flow.collectLatest


class ArtistsFragment : BaseFragment<FragmentArtistsBinding, ArtistsViewModel>
    (R.layout.fragment_artists, ArtistsViewModel::class) {

    private val artistsAdapter = ArtistsAdapter()
    private var isFromPopStack = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        navController.currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(IS_FROM_POP_STACK)?.observe(
            viewLifecycleOwner
        ) { result -> isFromPopStack = result }

        binding.searchView.setOnQueryTextListener(
            DebouncingQueryTextListener(
                this.lifecycle
            ) { newText ->
                newText?.let {
                    if (newText.isNotEmpty()) {
                        if (!isFromPopStack) {
                            newText.let { viewModel.getArtists(it) }
                            initArtistsRecyclerView()
                        }
                        isFromPopStack = false
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
//                launchOnLifecycleScope {
//                    artistsFlow?.collectLatest { submitData(it) }
//                }
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


    override fun onUIEventTriggered(event: UIEvent) {
        when (event) {
            is UIEvent.NavigateToAlbums -> {
                val directions =
                    event.artistsData.name?.let { ArtistsFragmentDirections.actionArtistsFragmentToAlbumsFragment(it) }
                directions?.let { findNavController().navigate(it) }

            }
            is UIEvent.RenderArtistsList -> {
                launchOnLifecycleScope {
                    artistsAdapter.submitData(event.artistsData)
                }
            }
        }
    }
}