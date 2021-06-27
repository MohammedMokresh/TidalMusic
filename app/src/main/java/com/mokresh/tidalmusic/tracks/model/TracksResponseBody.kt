package com.mokresh.tidalmusic.tracks.model


import com.google.gson.annotations.SerializedName
import com.mokresh.tidalmusic.base.model.Error

data class TracksResponseBody(
    @SerializedName("data")
    val tracksData: ArrayList<TracksData>?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("error")
    val error: Error?
)