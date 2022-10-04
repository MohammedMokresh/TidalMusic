package com.mokresh.tidalmusic.albums.model


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.mokresh.tidalmusic.artist.models.ArtistsData
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "album")
data class AlbumsData(
    @Embedded @SerializedName("artist")
    val artist: ArtistsData?,
    @ColumnInfo(name = "coverMedium") @SerializedName("cover_medium")
    val coverMedium: String?,
    @PrimaryKey @ColumnInfo(name = "id_album") @SerializedName("id")
    val id: String,
    @ColumnInfo(name = "title") @SerializedName("title")
    val title: String?,
) : Parcelable