package com.mokresh.tidalmusic.albums.data

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mokresh.tidalmusic.albums.model.AlbumsData
import com.mokresh.tidalmusic.api.ApiServices
import com.mokresh.tidalmusic.api.remote.NetworkResponse
import com.mokresh.tidalmusic.utils.Constants

class AlbumsPagingDataSource(private val service: ApiServices, private val query: String) :
    PagingSource<Int, AlbumsData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AlbumsData> {
        val pageNumber = params.key ?: 1
        return try {
            val response = service.getAlbums(query, pageNumber)
            when (response) {
                is NetworkResponse.Success -> {
                    val pagedResponse = response.body
                    val data = pagedResponse.albumsData
                    var nextPageNumber: Int? = null
                    if (!pagedResponse.next.isNullOrEmpty()) {
                        val uri = Uri.parse(pagedResponse.next)
                        val nextPageQuery = uri.getQueryParameter("index")
                        nextPageNumber = nextPageQuery?.toInt()
                    }
                    return LoadResult.Page(
                        data = data.orEmpty(),
                        prevKey = null,
                        nextKey = nextPageNumber
                    )

                }
                is NetworkResponse.ApiError -> return LoadResult.Error(Throwable(response.body.error?.message))
                is NetworkResponse.NetworkError -> return LoadResult.Error(Throwable(Constants.GENERAL_ERROR_MESSAGE))
                is NetworkResponse.UnknownError -> return LoadResult.Error(Throwable(Constants.GENERAL_ERROR_MESSAGE))
            }


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AlbumsData>): Int = 1
}