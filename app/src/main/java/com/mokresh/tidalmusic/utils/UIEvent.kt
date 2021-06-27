package com.mokresh.tidalmusic.utils

import com.mokresh.tidalmusic.albums.model.AlbumsData
import com.mokresh.tidalmusic.artist.models.ArtistsData
import com.mokresh.tidalmusic.tracks.TracksData


abstract class UIEvent {

    data class NavigateToAlbums(val artistsData: ArtistsData) : UIEvent()
    data class NavigateToTracks(val albumsData: AlbumsData) : UIEvent()

    data class RenderTracksList(val tracksData: ArrayList<TracksData>?) : UIEvent()

}