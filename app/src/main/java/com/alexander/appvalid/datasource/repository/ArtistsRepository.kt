package com.alexander.appvalid.datasource.repository

import androidx.paging.LivePagedListBuilder
import com.alexander.appvalid.datasource.repository.cache.ArtistsLocalCache
import com.alexander.appvalid.datasource.repository.callback.ArtistBoundaryCallback
import com.alexander.appvalid.models.ArtistsRepoResult
import com.alexander.appvalid.network.ServiceArtists

class ArtistsRepository(private val service: ServiceArtists, private val cache: ArtistsLocalCache) {

    fun getAllArtists(): ArtistsRepoResult {

        val sourceFactory = cache.getAllArtists()
        val boundaryCallback = ArtistBoundaryCallback(service, cache)
        val errors = boundaryCallback.errors
        val data = LivePagedListBuilder(sourceFactory, DATABASE_PAGE_SIZE)
            .setBoundaryCallback(boundaryCallback)
            .build()

        return ArtistsRepoResult(data, errors)
    }

    companion object {
        private const val DATABASE_PAGE_SIZE = 20
    }

}