package com.mokresh.tidalmusic.utils

import androidx.paging.PagingData
import com.mokresh.tidalmusic.albums.model.AlbumsData
import com.mokresh.tidalmusic.artist.models.ArtistsData
import com.mokresh.tidalmusic.tracks.model.TracksData


abstract class UIEvent {

    data class NavigateToAlbums(val artistsData: ArtistsData) : UIEvent()

    data class NavigateToTracks(val albumsData: AlbumsData) : UIEvent()

    data class RenderTracksList(val tracksData: ArrayList<TracksData>?) : UIEvent()

    data class RenderArtistsList(val artistsData: PagingData<ArtistsData>) : UIEvent()

    data class RenderAlbumsList(val albumsData: PagingData<AlbumsData>) : UIEvent()

    data class UpdateIsFavouriteAlbum(val albumsData: AlbumsData,val isFavourite:Boolean) : UIEvent()

    object OnBackPressed : UIEvent()


}