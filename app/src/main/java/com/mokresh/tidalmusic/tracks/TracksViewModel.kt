package com.mokresh.tidalmusic.tracks

import androidx.lifecycle.viewModelScope
import com.mokresh.tidalmusic.api.remote.NetworkResponse
import com.mokresh.tidalmusic.artist.data.ListsRepository
import com.mokresh.tidalmusic.base.BaseViewModel
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
                    is NetworkResponse.ApiError -> TODO()
                    is NetworkResponse.NetworkError -> TODO()
                    is NetworkResponse.Success -> TODO()
                    is NetworkResponse.UnknownError -> TODO()
                }
            }
        }
//        } else {
//            publishUIEvent(ShowNoNetworkConnectivity)
//        }
    }


}