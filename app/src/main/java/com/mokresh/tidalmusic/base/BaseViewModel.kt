package com.mokresh.tidalmusic.base

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dansdev.libeventpipe.EventPipe
import com.mokresh.tidalmusic.utils.SingleLiveEvent
import com.mokresh.tidalmusic.utils.UIEvent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(), LifecycleObserver {
    var errorMessage = SingleLiveEvent<String>()
    val isDataEmpty = ObservableBoolean(false)


    inline fun <T> launchPagingAsync(
        crossinline execute: suspend () -> Flow<T>,
        crossinline onSuccess: (Flow<T>) -> Unit
    ) {
        viewModelScope.launch {
            try {
                Log.e("asd","as")

                val result = execute()
                onSuccess(result)
            } catch (ex: Exception) {
                Log.e("asd",ex.message+"as")
                errorMessage.value = ex.message
            }
        }
    }


    fun <T : UIEvent> publishUIEvent(event: T) {
        EventPipe.send(event)
    }

    fun onBackPressed() {
        publishUIEvent(UIEvent.OnBackPressed)
    }

}
