package com.mokresh.tidalmusic.albums.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.mokresh.tidalmusic.artist.models.ArtistsData
import kotlinx.parcelize.Parcelize

@Parcelize
data class AlbumsData(
    @SerializedName("artist")
    val artist: ArtistsData?,
    @SerializedName("cover_medium")
    val coverMedium: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("title")
    val title: String?,
) : Parcelable