package com.mokresh.tidalmusic.artist.models


import com.google.gson.annotations.SerializedName

data class ArtistsData(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("picture")
    val picture: String?,

    @SerializedName("type")
    val type: String?,

    )