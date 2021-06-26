package com.mokresh.tidalmusic.artist.data

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mokresh.tidalmusic.artist.data.ListsRepository
import com.mokresh.tidalmusic.artist.models.ArtistsData
import com.mokresh.tidalmusic.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class ArtistsViewModel(
    private val listsRepository: ListsRepository,

    ) : BaseViewModel() {
    var artistsFlow: Flow<PagingData<ArtistsData>>? = null


    fun getArtists(query: String) = launchPagingAsync({
        listsRepository.getArtists(query).cachedIn(viewModelScope)
    }, {
        artistsFlow = it
    })

}