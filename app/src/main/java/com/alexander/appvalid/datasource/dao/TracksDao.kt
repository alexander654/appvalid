package com.alexander.appvalid.datasource.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alexander.appvalid.models.Track
import com.alexander.appvalid.utils.Constants

@Dao
interface TracksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTracks(tracks: List<Track>)

    @Query("SELECT * from ${Constants.TABLE_TRACKS}")
    fun getAllTracks(): DataSource.Factory<Int, Track>

}