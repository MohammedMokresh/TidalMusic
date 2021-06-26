package com.mokresh.tidalmusic.artist.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mokresh.tidalmusic.api.ApiServices
import com.mokresh.tidalmusic.artist.data.models.GetArtistsData
import kotlinx.coroutines.flow.Flow

class ArtistsRepository(
    private val service: ApiServices,
) {

    fun getArtists(query: String): Flow<PagingData<GetArtistsData>> = Pager(
        config = PagingConfig(pageSize = 20, prefetchDistance = 2),
        pagingSourceFactory = { ArtistsPagingDataSource(service, query) }
    ).flow

}
