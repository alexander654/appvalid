package com.alexander.appvalid.activities

import androidx.lifecycle.ViewModel
import com.alexander.appvalid.datasource.repository.ArtistsRepository

class MainViewModel(private val repository: ArtistsRepository) : ViewModel() {

    val artistsResult
        get() = repository.getAllArtists().data
    val errors
        get() = repository.getAllArtists().errors

    fun getArtists() {
        repository.getAllArtists()
    }
}