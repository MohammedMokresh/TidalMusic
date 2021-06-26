package com.mokresh.tidalmusic.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mokresh.tidalmusic.databinding.ItemArtistBinding
import com.mokresh.tidalmusic.artist.data.models.GetArtistsData

class ArtistsAdapter :
    PagingDataAdapter<GetArtistsData, ArtistsAdapter.ArtistsViewHolder>(ArtistsComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArtistsViewHolder(
            ItemArtistBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ArtistsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ArtistsViewHolder(private val binding: ItemArtistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GetArtistsData) = with(binding) {
            artist = item
        }
    }

    object ArtistsComparator : DiffUtil.ItemCallback<GetArtistsData>() {
        override fun areItemsTheSame(oldItem: GetArtistsData, newItem: GetArtistsData) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GetArtistsData, newItem: GetArtistsData) =
            oldItem == newItem
    }
}