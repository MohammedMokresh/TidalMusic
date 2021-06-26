package com.mokresh.tidalmusic.albums

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mokresh.tidalmusic.albums.model.AlbumsData
import com.mokresh.tidalmusic.artist.data.ListsRepository
import com.mokresh.tidalmusic.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class AlbumsViewModel(
    private val listsRepository: ListsRepository,

    ) : BaseViewModel() {
    var albumsFlow: Flow<PagingData<AlbumsData>>? = null


    fun getArtists(query: String) = launchPagingAsync({
        listsRepository.getAlbums(query).cachedIn(viewModelScope)
    }, {
        albumsFlow = it
    })

}