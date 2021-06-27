package com.mokresh.tidalmusic.artist.view

import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.artist.models.ArtistsData
import com.mokresh.tidalmusic.base.GenericPagerAdapter
import com.mokresh.tidalmusic.base.OnClickListener
import com.mokresh.tidalmusic.databinding.ItemArtistBinding
import com.mokresh.tidalmusic.utils.UIEvent

class ArtistsAdapter :
    GenericPagerAdapter<ArtistsData, ItemArtistBinding>(
        layoutId = R.layout.item_artist,
    ) {

    override fun onItemClick(item: ArtistsData) {
        super.onItemClick(item)
        publishUIEvent(UIEvent.NavigateToAlbums(item))

    }
}

