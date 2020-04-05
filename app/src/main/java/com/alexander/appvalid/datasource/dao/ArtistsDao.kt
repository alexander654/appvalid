package com.alexander.appvalid.datasource.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexander.appvalid.models.Artist
import com.alexander.appvalid.utils.Constants

@Dao
interface ArtistsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveArtists(artists: List<Artist>)

    @Query("SELECT * from ${Constants.TABLE_ARTISTS}")
    fun getAllArtists(): DataSource.Factory<Int, Artist>

}