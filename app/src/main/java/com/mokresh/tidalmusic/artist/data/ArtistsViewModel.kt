package com.mokresh.tidalmusic.artist.data

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mokresh.tidalmusic.api.remote.NetworkResponse
import com.mokresh.tidalmusic.artist.data.ListsRepository
import com.mokresh.tidalmusic.artist.models.ArtistsData
import com.mokresh.tidalmusic.base.BaseViewModel
import com.mokresh.tidalmusic.utils.Constants
import com.mokresh.tidalmusic.utils.UIEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ArtistsViewModel(
    private val listsRepository: ListsRepository,

    ) : BaseViewModel() {
//    var artistsFlow: Flow<PagingData<ArtistsData>>? = null


    fun getArtists(query: String) {

        viewModelScope.launch {
            listsRepository.getArtists(query).collect { publishUIEvent(UIEvent.RenderArtistsList(it)) }
        }
    }


//    fun getArtists(query: String) = launchPagingAsync({
//        listsRepository.getArtists(query).cachedIn(viewModelScope)
//    }, {
//        artistsFlow = it
//    })

}