package com.mokresh.tidalmusic.tracks

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.albums.AlbumsFragmentArgs
import com.mokresh.tidalmusic.albums.model.AlbumsData
import com.mokresh.tidalmusic.base.BaseFragment
import com.mokresh.tidalmusic.databinding.FragmentTracksBinding
import com.mokresh.tidalmusic.utils.Constants
import com.mokresh.tidalmusic.utils.UIEvent


class TracksFragment : BaseFragment<FragmentTracksBinding, TracksViewModel>
    (R.layout.fragment_tracks, TracksViewModel::class) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            val album: AlbumsData = TracksFragmentArgs.fromBundle(requireArguments()).album
            binding.album = album
            album.id?.toInt()?.let { viewModel.getTracks(it) }
        }
    }

    override fun onUIEventTriggered(event: UIEvent) {
        when (event) {
            is UIEvent.RenderTracksList -> {
                binding.tracksRecyclerView.adapter = event.tracksData?.let { TrackAdapter(it) }
            }
        }
    }

}