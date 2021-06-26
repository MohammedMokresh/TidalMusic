package com.mokresh.tidalmusic.albums

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mokresh.tidalmusic.albums.model.AlbumsData
import com.mokresh.tidalmusic.api.ApiServices

class AlbumsPagingDataSource(private val service: ApiServices, private val query: String) :
    PagingSource<Int, AlbumsData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AlbumsData> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getAlbums(query, pageNumber)
            val pagedResponse = response.body()
            val data = pagedResponse?.albumsData

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

    override fun getRefreshKey(state: PagingState<Int, AlbumsData>): Int = 1
}