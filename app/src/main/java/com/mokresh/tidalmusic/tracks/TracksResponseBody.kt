package com.mokresh.tidalmusic.tracks


import com.google.gson.annotations.SerializedName

data class TracksResponseBody(
    @SerializedName("data")
    val tracksData: List<TracksData>?,
    @SerializedName("total")
    val total: Int?
)