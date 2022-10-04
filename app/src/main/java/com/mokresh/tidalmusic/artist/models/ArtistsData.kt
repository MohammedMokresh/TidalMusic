package com.mokresh.tidalmusic.artist.models


import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtistsData(
    @ColumnInfo(name = "id_artist")  @SerializedName("id")
    val id: String,

    @ColumnInfo(name = "name_artist")  @SerializedName("name")
    val name: String?,

    @ColumnInfo(name = "picture_artist") @SerializedName("picture")
    val picture: String?,

    @ColumnInfo(name = "type_artist") @SerializedName("type")
    val type: String?,

    ) : Parcelable