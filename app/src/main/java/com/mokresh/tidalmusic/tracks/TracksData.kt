package com.mokresh.tidalmusic.tracks


import com.google.gson.annotations.SerializedName
import com.mokresh.tidalmusic.artist.models.ArtistsData

data class TracksData(
    @SerializedName("track_position")
    val trackPosition: Int?,

    @SerializedName("artist")
    val artist: ArtistsData?,

    @SerializedName("title_short")
    val titleShort: String?,

    )