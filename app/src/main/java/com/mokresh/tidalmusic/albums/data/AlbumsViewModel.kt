package com.mokresh.tidalmusic.albums.data

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mokresh.tidalmusic.albums.model.AlbumsData
import com.mokresh.tidalmusic.api.ListsRepository
import com.mokresh.tidalmusic.base.BaseViewModel
import com.mokresh.tidalmusic.utils.UIEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AlbumsViewModel(
    private val listsRepository: ListsRepository,

    ) : BaseViewModel() {

    fun getAlbums(query: String) {
        viewModelScope.launch {
            listsRepository.getAlbums(query).collectLatest { publishUIEvent(UIEvent.RenderAlbumsList(it)) }
        }
    }


}