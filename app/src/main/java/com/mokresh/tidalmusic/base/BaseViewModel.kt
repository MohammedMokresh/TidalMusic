package com.mokresh.tidalmusic.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokresh.tidalmusic.utils.SingleLiveEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    var progressLiveEvent = SingleLiveEvent<Boolean>()
    var errorMessage = SingleLiveEvent<String>()

    inline fun <T> launchPagingAsync(
        crossinline execute: suspend () -> Flow<T>,
        crossinline onSuccess: (Flow<T>) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val result = execute()
                onSuccess(result)
            } catch (ex: Exception) {
                errorMessage.value = ex.message
            }
        }
    }


}
