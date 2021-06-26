package com.mokresh.tidalmusic.albums

import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.albums.model.AlbumsData
import com.mokresh.tidalmusic.base.GenericPagerAdapter
import com.mokresh.tidalmusic.databinding.ItemAlbumBinding

class AlbumsAdapter :
    GenericPagerAdapter<AlbumsData, ItemAlbumBinding>(layoutId = R.layout.item_album)

