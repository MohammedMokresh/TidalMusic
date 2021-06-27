package com.mokresh.tidalmusic.base.model


import com.google.gson.annotations.SerializedName

data class ErrorBody(
    @SerializedName("error")
    val error: Error?
)