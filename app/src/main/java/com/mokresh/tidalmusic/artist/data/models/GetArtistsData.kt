package com.mokresh.tidalmusic.artist.data.models


import com.google.gson.annotations.SerializedName

data class GetArtistsData(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("picture")
    val picture: String?,

    @SerializedName("type")
    val type: String?,


    )