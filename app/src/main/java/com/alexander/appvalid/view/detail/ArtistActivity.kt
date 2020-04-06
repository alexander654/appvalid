package com.alexander.appvalid.view.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alexander.appvalid.R
import com.alexander.appvalid.databinding.ActivityArtistBinding
import com.alexander.appvalid.models.Artist
import com.alexander.appvalid.utils.Constants
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class ArtistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist)
        binding.lifecycleOwner = this

        intent.getSerializableExtra(Constants.SELECTED_ARTIST)?.let {
            try {
                it as Artist
                binding.artist = it
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.we_had_trouble), Toast.LENGTH_SHORT).show()
            }
        }

        binding.artist?.image?.last()?.text?.let {
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
                binding.artist?.url?.let { url ->
                    startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(url)))
                }
            } catch (e: Exception) {
                Toast.makeText(this, getString(R.string.we_had_trouble), Toast.LENGTH_SHORT).show()
            }
        }

        binding.goBack.setOnClickListener { finish() }
    }
}
