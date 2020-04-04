package com.alexander.appvalid.network.contracts

import com.alexander.appvalid.BuildConfig
import com.alexander.appvalid.models.TopTracksResponse
import com.alexander.appvalid.utils.Constants
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TopTracksContract {

    @Headers("Content-Type: application/json;charset=utf-8", "Accept: application/json")
    @GET("2.0/")
    fun getTopTracks(
        @Query("limit") limit: Long?,
        @Query("page") page: Long?,
        @Query("location") location: String?,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("method") method: String = Constants.METHOD_TOP_TRACKS,
        @Query("country") country: String = Constants.DEFAULT_COUNTRY,
        @Query("format") format: String = Constants.DEFAULT_FORMAT
    ): Single<Response<TopTracksResponse>>

}