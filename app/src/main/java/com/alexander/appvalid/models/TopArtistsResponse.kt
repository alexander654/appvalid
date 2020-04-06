package com.alexander.appvalid.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TopArtistsResponse(
    @SerializedName("topartists")
    val topArtists: ArtistsData
) : Serializable