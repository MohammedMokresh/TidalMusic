package com.mokresh.tidalmusic.albums.data

import androidx.lifecycle.viewModelScope
import com.mokresh.tidalmusic.api.ListsRepository
import com.mokresh.tidalmusic.base.BaseViewModel
import com.mokresh.tidalmusic.utils.UIEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AlbumsViewModel(
    private val listsRepository: ListsRepository,

    ) : BaseViewModel() {

     fun getAlbums(query: String) {
        viewModelScope.launch {
            listsRepository.getAlbums(query).collectLatest {
                try {
                    publishUIEvent(UIEvent.RenderAlbumsList(it))
                } catch (ex: Exception) {
                    errorMessage.value = ex.message
                }
            }
        }
    }


}