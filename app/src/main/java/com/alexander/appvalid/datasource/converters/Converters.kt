package com.alexander.appvalid.datasource.converters

import androidx.room.TypeConverter
import com.alexander.appvalid.models.Artist
import com.alexander.appvalid.models.Image
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromImages(images: List<Image>): String {
        return if (images.isNullOrEmpty()) {
            ""
        } else {
            val gson = Gson()
            val type = object : TypeToken<List<Image>>() {}.type
            gson.toJson(images, type)
        }
    }

    @TypeConverter
    fun toListImages(value: String): List<Image> {
        val gson = Gson()
        val type = object : TypeToken<List<Image>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromArtist(artist: Artist): String {
        return if (artist == null) {
            ""
        } else {
            val gson = Gson()
            val type = object : TypeToken<Artist>() {}.type
            gson.toJson(artist, type)
        }
    }

    @TypeConverter
    fun toArtist(value: String): Artist {
        val gson = Gson()
        val type = object : TypeToken<Artist>() {}.type
        return gson.fromJson(value, type)
    }

}