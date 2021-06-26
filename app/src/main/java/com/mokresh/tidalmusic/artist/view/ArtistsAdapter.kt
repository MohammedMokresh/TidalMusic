package com.mokresh.tidalmusic.artist.view

import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.artist.models.ArtistsData
import com.mokresh.tidalmusic.base.GenericPagerAdapter
import com.mokresh.tidalmusic.base.OnClickListener
import com.mokresh.tidalmusic.databinding.ItemArtistBinding

class ArtistsAdapter(onClickListener: OnClickListener<ArtistsData>) :
    GenericPagerAdapter<ArtistsData, ItemArtistBinding>(
        layoutId = R.layout.item_artist,
        clickListener = onClickListener
    ) {

//    override fun onItemClick(item: ArtistsData) {
//        super.onItemClick(item)
//        Navigation.findNavController(pagerAdapterBinding.root)
//            .navigate(R.id.action_artistsFragment_to_albumsFragment)
//    }
}

