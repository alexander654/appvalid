package com.alexander.appvalid.models

import androidx.room.Entity
import com.alexander.appvalid.utils.Constants
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Streamable(
    @SerializedName("fulltrack")
    var fullTrack: Long = 0L,
    @SerializedName("#text")
    var text: Long = 0L
) : Serializable