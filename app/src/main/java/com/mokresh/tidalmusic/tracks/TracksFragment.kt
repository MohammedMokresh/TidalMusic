package com.mokresh.tidalmusic.tracks

import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.base.BaseFragment
import com.mokresh.tidalmusic.databinding.FragmentTracksBinding
import com.mokresh.tidalmusic.utils.UIEvent


class TracksFragment : BaseFragment<FragmentTracksBinding, TracksViewModel>
    (R.layout.fragment_tracks, TracksViewModel::class) {


    override fun onUIEventTriggered(event: UIEvent) {
        TODO("Not yet implemented")
    }

}