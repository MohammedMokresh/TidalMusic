package com.mokresh.tidalmusic.artist.view

import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.artist.models.ArtistsData
import com.mokresh.tidalmusic.base.GenericPagerAdapter
import com.mokresh.tidalmusic.databinding.ItemArtistBinding

class ArtistsAdapter :
    GenericPagerAdapter<ArtistsData, ItemArtistBinding>(layoutId = R.layout.item_artist)

