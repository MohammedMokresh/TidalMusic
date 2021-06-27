package com.mokresh.tidalmusic.artist.models


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtistsData(
    @SerializedName("id")
    val id: String,

    @SerializedName("name")
    val name: String?,

    @SerializedName("picture")
    val picture: String?,

    @SerializedName("type")
    val type: String?,

    ) : Parcelable