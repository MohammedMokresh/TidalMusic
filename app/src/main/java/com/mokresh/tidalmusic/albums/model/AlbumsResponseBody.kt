package com.mokresh.tidalmusic.albums.model


import com.google.gson.annotations.SerializedName
import com.mokresh.tidalmusic.model.Error

data class AlbumsResponseBody(
    @SerializedName("data")
    val albumsData: List<AlbumsData>?,
    @SerializedName("next")
    val next: String?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("error")
    val error: Error?
)