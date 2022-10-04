package com.mokresh.tidalmusic.favourites

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.albums.data.AlbumsViewModel
import com.mokresh.tidalmusic.base.BaseFragment
import com.mokresh.tidalmusic.databinding.FragmentFavouritesBinding
import com.mokresh.tidalmusic.utils.Constants
import com.mokresh.tidalmusic.utils.UIEvent


class FavouritesFragment : BaseFragment<FragmentFavouritesBinding, AlbumsViewModel>
    (R.layout.fragment_favourites, AlbumsViewModel::class) {

    private val favouritesAdapter = FavouritesAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getFavourites()

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
        with(favouritesAdapter) {
            binding.rvAlbums.adapter = this
            viewModel.favouriteAlbums.observe(viewLifecycleOwner) {
                setItems(it)
            }
        }
    }

    override fun onUIEventTriggered(event: UIEvent) {
        when (event) {
            is UIEvent.NavigateToTracks -> {
                val directions =
                    event.albumsData.let {
                        FavouritesFragmentDirections.actionFavouritesFragmentToTracksFragment(
                            it
                        )
                    }
                directions.let { findNavController().navigate(it) }
            }

            is UIEvent.UpdateIsFavouriteAlbum -> {
                if (event.isFavourite) {
                    viewModel.insertFavourite(event.albumsData)
                } else {
                    viewModel.deleteFavourite(event.albumsData.id)
                    viewModel.getFavourites()
                }
            }
        }
    }
}