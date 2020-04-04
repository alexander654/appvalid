package com.alexander.appvalid.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alexander.appvalid.R
import com.alexander.appvalid.network.ServiceArtists

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ServiceArtists().getArtists(
            limit = 10,
            page = 1,
            onSuccess = { response ->
                Log.d("RESPONSE", response.topArtists.topArtists.size.toString())
            },
            onError = { message ->
                Log.d("ERROR", message)
            })
    }
}
