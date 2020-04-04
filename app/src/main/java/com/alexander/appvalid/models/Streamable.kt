package com.alexander.appvalid.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Streamable(
    @SerializedName("fulltrack")
    val fullTrack: Long = 0L,
    @SerializedName("#text")
    val text: Long = 0L
) : Serializable