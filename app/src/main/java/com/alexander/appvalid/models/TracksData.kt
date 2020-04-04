package com.alexander.appvalid.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TracksData(
    val track: List<Track> = ArrayList(),
    @SerializedName("@attr")
    val attributes: AttributesResponse
) : Serializable