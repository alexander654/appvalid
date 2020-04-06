package com.alexander.appvalid.view.home.viewmodel

import androidx.lifecycle.ViewModel
import com.alexander.appvalid.datasource.repository.ArtistsRepository
import com.alexander.appvalid.datasource.repository.TracksRepository

class MainViewModel(
    private val repository: ArtistsRepository,
    private val repositoryTracks: TracksRepository
) : ViewModel() {

    val artistsResult
        get() = repository.getAllArtists().data
    val errors
        get() = repository.getAllArtists().errors

    fun getArtists() {
        repository.getAllArtists()
    }

    val tracksResult
        get() = repositoryTracks.getAllTracks().data

    val trackErrors
        get() = repositoryTracks.getAllTracks().errors

    fun getTracks() {
        repositoryTracks.getAllTracks()
    }
}