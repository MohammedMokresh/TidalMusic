package com.mokresh.tidalmusic.tracks


import com.google.gson.annotations.SerializedName
import com.mokresh.tidalmusic.model.Error

data class TracksResponseBody(
    @SerializedName("data")
    val tracksData: List<TracksData>?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("error")
    val error: Error?
)