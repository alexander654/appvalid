package com.alexander.appvalid.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Track(
    val name: String = "",
    val duration: Long = 0L,
    val listeners: Long = 0L,
    val mbid: String = "",
    val streamable: Streamable,
    val artist: Artist,
    val image: List<Image>,
    @SerializedName("@attr")
    val attributes: TrackAttributes
) : Serializable