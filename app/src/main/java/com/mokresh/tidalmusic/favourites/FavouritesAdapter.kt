package com.mokresh.tidalmusic.favourites

import androidx.core.content.ContextCompat
import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.albums.model.AlbumsData
import com.mokresh.tidalmusic.base.GenericAdapter
import com.mokresh.tidalmusic.base.GenericViewHolder
import com.mokresh.tidalmusic.databinding.ItemAlbumBinding
import com.mokresh.tidalmusic.utils.UIEvent

class FavouritesAdapter(items: List<AlbumsData?>) :
    GenericAdapter<AlbumsData, ItemAlbumBinding>(layoutId = R.layout.item_album, items = items) {

    override fun onItemClick(item: AlbumsData) {
        super.onItemClick(item)
        publishUIEvent(UIEvent.NavigateToTracks(item))
    }

    override fun onBindViewHolder(
        holder: GenericViewHolder<AlbumsData, ItemAlbumBinding>,
        position: Int
    ) {
        setImageDrawable(holder, R.drawable.ic_baseline_favorite_24)
        holder.binding.favouriteImageView.setOnClickListener {
            if (holder.binding.favouriteImageView.drawable.constantState?.equals(
                    ContextCompat.getDrawable(
                        it.context,
                        R.drawable.ic_baseline_favorite_border_24
                    )?.constantState
                ) == true
            ) {
                setImageDrawable(holder, R.drawable.ic_baseline_favorite_24)
                holder.binding.item?.let {
                    publishUIEvent(UIEvent.UpdateIsFavouriteAlbum(holder.binding.item!!, true))
                }

            } else {
                setImageDrawable(holder, R.drawable.ic_baseline_favorite_border_24)
                holder.binding.item?.let {
                    publishUIEvent(UIEvent.UpdateIsFavouriteAlbum(holder.binding.item!!, false))
                }
            }
        }

        super.onBindViewHolder(holder, position)

    }


    private fun setImageDrawable(
        holder: GenericViewHolder<AlbumsData, ItemAlbumBinding>,
        drawable: Int
    ) {
        holder.binding.favouriteImageView.setImageDrawable(
            ContextCompat.getDrawable(
                holder.binding.favouriteImageView.context,
                drawable
            )
        )

    }
}