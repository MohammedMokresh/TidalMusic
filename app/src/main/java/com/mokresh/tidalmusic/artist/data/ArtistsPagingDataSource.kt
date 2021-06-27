package com.mokresh.tidalmusic.artist.data

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mokresh.tidalmusic.api.ApiServices
import com.mokresh.tidalmusic.artist.models.ArtistsData

class ArtistsPagingDataSource(private val service: ApiServices, private val query: String) :
    PagingSource<Int, ArtistsData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtistsData> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getArtist(query, pageNumber)
            val pagedResponse = response.body()
            val data = pagedResponse?.artistsData


            if (pagedResponse?.error != null) {
              return  LoadResult.Error(Throwable(pagedResponse.error.message))
            }

            var nextPageNumber: Int? = null
            if (!pagedResponse?.next.isNullOrEmpty()) {
                val uri = Uri.parse(pagedResponse?.next)
                val nextPageQuery = uri.getQueryParameter("index")
                nextPageNumber = nextPageQuery?.toInt()
            }

            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArtistsData>): Int = 1
}