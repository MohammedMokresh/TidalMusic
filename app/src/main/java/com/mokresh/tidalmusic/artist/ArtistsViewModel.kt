package com.mokresh.tidalmusic.artist

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mokresh.tidalmusic.artist.data.ArtistsRepository
import com.mokresh.tidalmusic.artist.data.models.GetArtistsData
import com.mokresh.tidalmusic.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

class ArtistsViewModel(
    private val artistsRepository: ArtistsRepository,

    ) : BaseViewModel() {
    private lateinit var _artistsFlow: Flow<PagingData<GetArtistsData>>
    val artistsFlow: Flow<PagingData<GetArtistsData>>
        get() = _artistsFlow


    fun getArtists(query: String) = launchPagingAsync({
        artistsRepository.getArtists(query).cachedIn(viewModelScope)
    }, {
        _artistsFlow = it
    })

}