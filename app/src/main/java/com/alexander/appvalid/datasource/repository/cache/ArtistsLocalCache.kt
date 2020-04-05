package com.alexander.appvalid.datasource.repository.cache

import androidx.paging.DataSource
import com.alexander.appvalid.datasource.dao.ArtistsDao
import com.alexander.appvalid.models.Artist
import java.util.concurrent.Executor

class ArtistsLocalCache(private val dao: ArtistsDao, private val executor: Executor) {

    fun insertArtistsIntoDatabase(artists: List<Artist>, onFinish: () -> Unit) {
        executor.execute {
            dao.saveArtists(artists)
            onFinish()
        }
    }

    fun getAllArtists(): DataSource.Factory<Int, Artist> = dao.getAllArtists()

}