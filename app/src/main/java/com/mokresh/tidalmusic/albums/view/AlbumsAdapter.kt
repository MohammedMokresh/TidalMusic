package com.mokresh.tidalmusic.albums.view

import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.albums.model.AlbumsData
import com.mokresh.tidalmusic.base.GenericPagerAdapter
import com.mokresh.tidalmusic.databinding.ItemAlbumBinding
import com.mokresh.tidalmusic.utils.UIEvent

class AlbumsAdapter : GenericPagerAdapter<AlbumsData, ItemAlbumBinding>(layoutId = R.layout.item_album) {

    override fun onItemClick(item: AlbumsData) {
        super.onItemClick(item)
        publishUIEvent(UIEvent.NavigateToTracks(item))
    }
}

