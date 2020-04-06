package com.alexander.appvalid.network

import com.alexander.appvalid.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class ApiBase {

    protected abstract val baseUrl: String
    private lateinit var retrofit: Retrofit
    private lateinit var clientBuilder: OkHttpClient.Builder
    private val interceptor = HttpLoggingInterceptor()

    protected fun <InterfaceType> build(
        ctcClass: Class<InterfaceType>,
        baseUrl: String
    ): InterfaceType {
        interceptor.level = HttpLoggingInterceptor.Level.valueOf(BuildConfig.HTTP_LOGGING_LEVEL)
        clientBuilder = OkHttpClient.Builder()
            .addInterceptor(interceptor)

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ctcClass)
    }

}