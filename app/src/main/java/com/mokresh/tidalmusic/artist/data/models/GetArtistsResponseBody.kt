package com.mokresh.tidalmusic.artist.data.models


import com.google.gson.annotations.SerializedName

data class GetArtistsResponseBody(
    @SerializedName("data")
    val getArtistsData: List<GetArtistsData>?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("total")
    val total: Int?
)