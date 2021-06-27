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
import com.mokresh.tidalmusic.utils.AppUtil
import com.mokresh.tidalmusic.utils.Constants.IS_FROM_POP_STACK
import com.mokresh.tidalmusic.utils.DebouncingQueryTextListener
import com.mokresh.tidalmusic.utils.UIEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map


class ArtistsFragment : BaseFragment<FragmentArtistsBinding, ArtistsViewModel>
    (R.layout.fragment_artists, ArtistsViewModel::class) {

    private val artistsAdapter = ArtistsAdapter()
    private var isFromPopStack = false
    private var checkForEmptyState = false

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
                            checkForEmptyState = true
                            newText.let { viewModel.getArtists(it) }
                        }
                        isFromPopStack = false
                    }
                }
            }
        )
        initArtistsRecyclerView()

    }

    private fun initArtistsRecyclerView() {
        with(artistsAdapter) {
            binding.swipeRefresh.setOnRefreshListener { refresh() }
            binding.rvArtists.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )
            launchOnLifecycleScope {
                loadStateFlow.collectLatest {
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
                            if (checkForEmptyState)
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
            is UIEvent.NavigateToAlbums -> {
                AppUtil.hideKeyboard(requireActivity())
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