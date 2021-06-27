package com.mokresh.tidalmusic.base

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

    fun <T : UIEvent> publishUIEvent(event: T) {
        EventPipe.send(event)
    }

    fun onBackPressed() {
        publishUIEvent(UIEvent.OnBackPressed)
    }

}
