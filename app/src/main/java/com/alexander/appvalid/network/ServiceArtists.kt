package com.alexander.appvalid.network

import com.alexander.appvalid.BuildConfig
import com.alexander.appvalid.models.TopArtistsResponse
import com.alexander.appvalid.models.TopTracksResponse
import com.alexander.appvalid.network.contracts.TopArtistsContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class ServiceArtists : ApiBase() {

    override val baseUrl: String
        get() = BuildConfig.API_BASE_URL

    private val service: TopArtistsContract = build(TopArtistsContract::class.java, baseUrl)

    fun getArtists(
        limit: Long?,
        page: Long?,
        onSuccess: (data: TopArtistsResponse) -> Unit,
        onError: (message: String) -> Unit
    ): Disposable {
        return service.getTopArtists(limit = limit, page = page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    when {
                        it.isSuccessful -> it.body()?.let(onSuccess)
                        else -> it.code().toString().let(onError)
                    }
                },
                onError = {
                    it.message?.let(onError)
                })
    }

}