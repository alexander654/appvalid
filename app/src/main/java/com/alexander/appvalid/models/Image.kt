package com.alexander.appvalid.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Image(
    @SerializedName("#text")
    var text: String = "",
    var size: String = ""
) : Serializable