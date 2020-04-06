package com.alexander.appvalid.datasource.repository

import androidx.paging.LivePagedListBuilder
import com.alexander.appvalid.datasource.repository.cache.TracksLocalCache
import com.alexander.appvalid.datasource.repository.callback.TracksBoundaryCallback
import com.alexander.appvalid.models.TracksRepoResult
import com.alexander.appvalid.network.ServiceTracks

class TracksRepository(private val service: ServiceTracks, private val cache: TracksLocalCache) {

    fun getAllTracks(): TracksRepoResult {
        val sourceFactory = cache.getAllTracks()
        val boundaryCallback = TracksBoundaryCallback(service, cache)
        val errors = boundaryCallback.errors
        val data = LivePagedListBuilder(sourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()
        return TracksRepoResult(data = data, errors = errors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }

}