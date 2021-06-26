package com.mokresh.tidalmusic.model


import com.google.gson.annotations.SerializedName

data class ErrorBody(
    @SerializedName("error")
    val error: Error?
)