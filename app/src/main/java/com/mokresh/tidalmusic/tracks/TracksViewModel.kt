package com.mokresh.tidalmusic.tracks

import androidx.lifecycle.viewModelScope
import com.mokresh.tidalmusic.R
import com.mokresh.tidalmusic.api.remote.NetworkResponse
import com.mokresh.tidalmusic.artist.data.ListsRepository
import com.mokresh.tidalmusic.base.BaseViewModel
import com.mokresh.tidalmusic.utils.Constants
import com.mokresh.tidalmusic.utils.UIEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TracksViewModel(
    private val listsRepository: ListsRepository,

    ) : BaseViewModel() {

    fun getTracks(id: Int) {

//        if (Utils.isNetworkAvailable(context)) {
        viewModelScope.launch {
            listsRepository.getTracks(id).collect {
                when (it) {
                    is NetworkResponse.ApiError -> errorMessage.value = it.body.error?.message
                    is NetworkResponse.NetworkError, is NetworkResponse.UnknownError -> errorMessage.value =
                        Constants.GENERAL_ERROR_MESSAGE
                    is NetworkResponse.Success -> publishUIEvent(UIEvent.RenderTracksList(it.body.tracksData))

                }
            }
        }
//        } else {
//            publishUIEvent(ShowNoNetworkConnectivity)
//        }
    }


}