package com.alexander.appvalid.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TopTracksResponse(
    val track: List<Track>,
    @SerializedName("@attr")
    val attributes: AttributesResponse
) : Serializable