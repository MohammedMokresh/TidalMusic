package com.mokresh.tidalmusic.base

import android.util.Log
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
                Log.e("test","hey")

                val result = execute()
                onSuccess(result)
            } catch (ex: Exception) {
                Log.e("test",ex.message+"a")
                errorMessage.value = ex.message
            }
        }
    }


}
