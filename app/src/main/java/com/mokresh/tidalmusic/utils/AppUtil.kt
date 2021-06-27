package com.mokresh.tidalmusic.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object AppUtil {
    fun hideKeyboard(activity: Activity?) {
        if (activity != null && activity.window != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
        }
    }

}