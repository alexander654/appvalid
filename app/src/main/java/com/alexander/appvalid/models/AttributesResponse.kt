package com.alexander.appvalid.models

import java.io.Serializable

data class AttributesResponse(
    val country: String = "",
    val page: Long = 0L,
    val perPage: Long = 0L,
    val totalPages: Long = 0L,
    val total: Long = 0L
) : Serializable