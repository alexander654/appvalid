package com.alexander.appvalid.base

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppValid : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidContext(this@AppValid)
            modules(modules)
        }
    }

    companion object {
        @get:Synchronized
        lateinit var instance: AppValid
            private set
    }
}