package com.alexander.appvalid.network.contracts

import com.alexander.appvalid.BuildConfig
import com.alexander.appvalid.models.TopArtistsResponse
import com.alexander.appvalid.utils.Constants
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface TopArtistsContract {

    @Headers("Content-Type: application/json;charset=utf-8", "Accept: application/json")
    @GET("2.0/")
    fun getTopArtists(
        @Query("limit") limit: Long?,
        @Query("page") page: Long?,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("method") method: String = Constants.METHOD_TOP_ARTISTS,
        @Query("country") country: String = Constants.DEFAULT_COUNTRY,
        @Query("format") format: String = Constants.DEFAULT_FORMAT
    ): Single<Response<TopArtistsResponse>>

}