package com.mokresh.tidalmusic.artist.models


import com.google.gson.annotations.SerializedName

data class ArtistsResponseBody(
    @SerializedName("data")
    val artistsData: List<ArtistsData>?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("total")
    val total: Int?
)