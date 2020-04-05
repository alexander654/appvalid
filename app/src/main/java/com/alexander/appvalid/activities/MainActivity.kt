package com.alexander.appvalid.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexander.appvalid.R
import com.alexander.appvalid.adapters.TestAdapter
import com.alexander.appvalid.adapters.TestAdapterTracks
import com.alexander.appvalid.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val adapter by inject<TestAdapter>()
    private val adapterTracks by inject<TestAdapterTracks>()
    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.rvTest.run {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            val manager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            layoutManager = manager
            adapter = this@MainActivity.adapter
        }

        binding.rvTestTrack.run {
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            val manager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            layoutManager = manager
            adapter = this@MainActivity.adapterTracks
        }

        viewModel.getArtists()
        viewModel.getTracks()
        viewModel.artistsResult.observe(this, Observer { data ->
            data?.let { adapter.submitList(it) }
        })
        viewModel.errors.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
        viewModel.tracksResult.observe(this, Observer { data ->
            data?.let { adapterTracks.submitList(it) }
        })
        viewModel.trackErrors.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }
}
