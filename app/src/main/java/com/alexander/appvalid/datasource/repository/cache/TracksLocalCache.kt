package com.alexander.appvalid.datasource.repository.cache

import androidx.paging.DataSource
import com.alexander.appvalid.datasource.dao.TracksDao
import com.alexander.appvalid.models.Track
import java.util.concurrent.Executor

class TracksLocalCache(private val dao: TracksDao, private val executor: Executor) {

    fun insertTracksIntoDatabase(tracks: List<Track>, onFinish: () -> Unit) {
        executor.execute {
            dao.saveTracks(tracks)
            onFinish()
        }
    }

    fun getAllTracks(): DataSource.Factory<Int, Track> = dao.getAllTracks()

}