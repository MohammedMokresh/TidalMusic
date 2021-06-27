package com.mokresh.tidalmusic.tracks.view

import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.base.GenericAdapter
import com.mokresh.tidalmusic.databinding.ItemTrackBinding
import com.mokresh.tidalmusic.tracks.model.TracksData


class TrackAdapter(items: List<TracksData?>) :
    GenericAdapter<TracksData, ItemTrackBinding>(layoutId = R.layout.item_track, items = items)