package com.mokresh.tidalmusic.tracks


import com.google.gson.annotations.SerializedName
import com.mokresh.tidalmusic.artist.models.ArtistsData

data class TracksData(
    @SerializedName("artist")
    val artist: ArtistsData?,
    @SerializedName("disk_number")
    val diskNumber: Int?,
    @SerializedName("duration")
    val duration: String?,
    @SerializedName("explicit_content_cover")
    val explicitContentCover: Int?,
    @SerializedName("explicit_content_lyrics")
    val explicitContentLyrics: Int?,
    @SerializedName("explicit_lyrics")
    val explicitLyrics: Boolean?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("isrc")
    val isrc: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("md5_image")
    val md5Image: String?,
    @SerializedName("preview")
    val preview: String?,
    @SerializedName("rank")
    val rank: String?,
    @SerializedName("readable")
    val readable: Boolean?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("title_short")
    val titleShort: String?,
    @SerializedName("title_version")
    val titleVersion: String?,
    @SerializedName("track_position")
    val trackPosition: Int?,
    @SerializedName("type")
    val type: String?
)