package com.alexander.appvalid.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alexander.appvalid.R
import com.alexander.appvalid.databinding.ActivityTrackBinding
import com.alexander.appvalid.models.Track
import com.alexander.appvalid.utils.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class TrackActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrackBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_track)
        binding.lifecycleOwner = this

        intent.getSerializableExtra(Constants.SELECTED_TRACK)?.let {
            try {
                it as Track
                binding.track = it
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.we_had_trouble), Toast.LENGTH_SHORT).show()
            }
        }

        binding.track?.image?.last()?.text?.let {
            Glide
                .with(this)
                .load(it)
                .override(500, 500)
                .centerCrop()
                .optionalCenterCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                .placeholder(R.drawable.ic_launcher_background)
                .into(binding.ivBack)
        }

        binding.btnGotLastFm.setOnClickListener {
            try {
                binding.track?.url?.let { url ->
                    startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))
                }
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.we_had_trouble), Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnGoBack.setOnClickListener { finish() }
    }
}
