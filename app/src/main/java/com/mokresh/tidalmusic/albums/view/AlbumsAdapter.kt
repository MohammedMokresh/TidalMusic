package com.mokresh.tidalmusic.albums.view

import androidx.core.content.ContextCompat
import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.albums.model.AlbumsData
import com.mokresh.tidalmusic.base.GenericPagerAdapter
import com.mokresh.tidalmusic.base.GenericViewHolder
import com.mokresh.tidalmusic.databinding.ItemAlbumBinding
import com.mokresh.tidalmusic.utils.UIEvent

class AlbumsAdapter :
    GenericPagerAdapter<AlbumsData, ItemAlbumBinding>(layoutId = R.layout.item_album) {

    override fun onItemClick(item: AlbumsData) {
        super.onItemClick(item)
        publishUIEvent(UIEvent.NavigateToTracks(item))
    }

    override fun onBindViewHolder(
        holder: GenericViewHolder<AlbumsData, ItemAlbumBinding>,
        position: Int
    ) {

        holder.binding.favouriteImageView.setOnClickListener {
            if (holder.binding.favouriteImageView.drawable.constantState?.equals(
                    ContextCompat.getDrawable(
                        it.context,
                        R.drawable.ic_baseline_favorite_border_24
                    )?.constantState
                ) == true
            ) {
                holder.binding.favouriteImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        it.context,
                        R.drawable.ic_baseline_favorite_24
                    )
                )

                holder.binding.item?.let {
                    publishUIEvent(UIEvent.UpdateIsFavouriteAlbum(holder.binding.item!!, true))
                }

            } else {
                holder.binding.favouriteImageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        it.context,
                        R.drawable.ic_baseline_favorite_border_24
                    )
                )
                holder.binding.item?.let {
                    publishUIEvent(UIEvent.UpdateIsFavouriteAlbum(holder.binding.item!!, false))
                }
            }
        }

        super.onBindViewHolder(holder, position)

    }
}

