package com.alexander.appvalid.datasource.repository.callback

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.alexander.appvalid.base.Utils
import com.alexander.appvalid.datasource.repository.cache.ArtistsLocalCache
import com.alexander.appvalid.models.Artist
import com.alexander.appvalid.network.ServiceArtists
import com.alexander.appvalid.utils.Constants

class ArtistBoundaryCallback(
    private val service: ServiceArtists,
    private val cache: ArtistsLocalCache
) : PagedList.BoundaryCallback<Artist>() {

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
        getAllArtists()
    }

    override fun onItemAtEndLoaded(itemAtEnd: Artist) {
        super.onItemAtEndLoaded(itemAtEnd)
        getAllArtists()
    }

    private fun getAllArtists() {
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
                        cache.insertArtistsIntoDatabase(data.topArtists.topArtists) {
                            isRequestInProgress = false
                            lastRequestedPage++
                        }
                    },
                    onError = { message ->
                        isRequestInProgress = false
                        _errors.value = message
                    })
            }
        }
    }
}