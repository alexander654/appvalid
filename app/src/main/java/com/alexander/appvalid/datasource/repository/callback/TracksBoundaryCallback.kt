package com.alexander.appvalid.datasource.repository.callback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.alexander.appvalid.base.Utils
import com.alexander.appvalid.datasource.repository.cache.TracksLocalCache
import com.alexander.appvalid.models.Track
import com.alexander.appvalid.network.ServiceTracks
import com.alexander.appvalid.utils.Constants

class TracksBoundaryCallback(
    private val service: ServiceTracks,
    private val cache: TracksLocalCache
) : PagedList.BoundaryCallback<Track>() {

    companion object {
        private const val NETWORK_PAGE_SIZE = 50
    }

    private var lastRequestedPage = 1

    private val _errors = MutableLiveData<String>()
    val errors: LiveData<String>
        get() = _errors

    private var isRequestInProgress = false

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        getAllTracks()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Track) {
        super.onItemAtEndLoaded(itemAtEnd)
        getAllTracks()
    }

    private fun getAllTracks() {
        when {
            !Utils.isConnectedToInternet -> {
                _errors.value = Constants.NO_INTERNET
                return
            }
            isRequestInProgress -> return
            else -> {
                isRequestInProgress = true
                service.getArtists(
                    NETWORK_PAGE_SIZE.toLong(),
                    lastRequestedPage.toLong(),
                    onSuccess = { data ->
                        cache.insertTracksIntoDatabase(data.tracks.track) {
                            isRequestInProgress = false
                            lastRequestedPage++
                        }
                    },
                    onError = { message ->
                        isRequestInProgress = false
                        _errors.value = message
                    },
                    location = null
                )
            }
        }
    }
}