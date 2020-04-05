package com.alexander.appvalid.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.alexander.appvalid.utils.Constants
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(
    tableName = Constants.TABLE_TRACKS
)
data class Track(
    var name: String,
    var duration: Long,
    var listeners: Long,
    @PrimaryKey
    var mbid: String,
    @Ignore
    @ColumnInfo(name = "stream_able")
    var streamable: Streamable,
    @Ignore
    @ColumnInfo(name = "artist_db")
    var artist: Artist,
    @Ignore
    @ColumnInfo(name = "image_db")
    var image: List<Image>,
    @Ignore
    @ColumnInfo(name = "attributes_db")
    @SerializedName("@attr")
    var attributes: TrackAttributes
) : Serializable {
    constructor() : this(
        "",
        0L,
        0L,
        "",
        Streamable(),
        Artist(),
        arrayListOf<Image>(),
        TrackAttributes()
    )
}