package com.alexander.appvalid.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ArtistsData (
    @SerializedName("artist")
    val topArtists: List<Artist> = ArrayList(),
    @SerializedName("@attr")
    val attributes: AttributesResponse
): Serializable