package com.mokresh.tidalmusic.artist.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mokresh.tidalmusic.albums.model.AlbumsData
import com.mokresh.tidalmusic.albums.AlbumsPagingDataSource
import com.mokresh.tidalmusic.api.ApiServices
import com.mokresh.tidalmusic.artist.models.ArtistsData
import kotlinx.coroutines.flow.Flow

class ListsRepository(
    private val service: ApiServices,
) {

    fun getArtists(query: String): Flow<PagingData<ArtistsData>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { ArtistsPagingDataSource(service, query) }
    ).flow


    fun getAlbums(query: String): Flow<PagingData<AlbumsData>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { AlbumsPagingDataSource(service, query) }
    ).flow

}
