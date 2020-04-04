package com.alexander.appvalid.models

import java.io.Serializable

data class Artist(
    val name: String = "",
    val listeners: Long = 0L,
    val mbid: String = "",
    val url: String = "",
    val streamable: Long = 0L,
    val image: List<Image> = ArrayList()
) : Serializable