package com.alexander.appvalid.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

object Utils {

    val isConnectedToInternet: Boolean
        get() {
            val manager =
                AppValid.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = manager.allNetworkInfo
            return info.any { it.state == NetworkInfo.State.CONNECTED }
        }

}