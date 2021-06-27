package com.mokresh.tidalmusic.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mokresh.tidalmusic.albums.model.AlbumsData
import com.mokresh.tidalmusic.albums.data.AlbumsPagingDataSource
import com.mokresh.tidalmusic.api.remote.NetworkResponse
import com.mokresh.tidalmusic.artist.data.ArtistsPagingDataSource
import com.mokresh.tidalmusic.artist.models.ArtistsData
import com.mokresh.tidalmusic.base.model.ErrorBody
import com.mokresh.tidalmusic.tracks.model.TracksResponseBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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


    suspend fun getTracks(id: Int): Flow<NetworkResponse<TracksResponseBody, ErrorBody>> {
        return flow {
            emit(service.getTracks(id = id))
        }.flowOn(Dispatchers.IO)
    }

}
