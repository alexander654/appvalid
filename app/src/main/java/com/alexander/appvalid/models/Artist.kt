package com.alexander.appvalid.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.alexander.appvalid.utils.Constants
import java.io.Serializable

@Entity(tableName = Constants.TABLE_ARTISTS)
data class Artist(
    var name: String = "",
    var listeners: Long = 0L,
    @PrimaryKey
    var mbid: String = "",
    var url: String = "",
    var streamable: Long = 0L,
    @Ignore
    var image: List<Image> = ArrayList()
) : Serializable