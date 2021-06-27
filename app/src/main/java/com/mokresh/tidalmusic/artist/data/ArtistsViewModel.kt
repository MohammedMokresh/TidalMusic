package com.mokresh.tidalmusic.artist.data

import androidx.lifecycle.viewModelScope
import com.mokresh.tidalmusic.api.ListsRepository
import com.mokresh.tidalmusic.base.BaseViewModel
import com.mokresh.tidalmusic.utils.UIEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ArtistsViewModel(
    private val listsRepository: ListsRepository,
) : BaseViewModel() {


    fun getArtists(query: String) {
        viewModelScope.launch {
            listsRepository.getArtists(query).collectLatest {
                try {
                    publishUIEvent(UIEvent.RenderArtistsList(it))
                } catch (ex: Exception) {
                    errorMessage.value = ex.message
                }
            }
        }
    }
}