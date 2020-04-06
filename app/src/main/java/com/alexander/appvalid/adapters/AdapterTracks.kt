package com.alexander.appvalid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexander.appvalid.R
import com.alexander.appvalid.databinding.ItemTrackBinding
import com.alexander.appvalid.models.Track
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.*

class AdapterTracks :
    PagedListAdapter<Track, AdapterTracks.ItemTrack>(TRACK_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTrack {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTrackBinding.inflate(inflater)
        return ItemTrack(binding)
    }

    override fun onBindViewHolder(holder: ItemTrack, position: Int) {
        val item = getItem(position)
        item?.let { it -> holder.bind(it) }
    }

    companion object {
        private val TRACK_COMPARATOR = object : DiffUtil.ItemCallback<Track>() {
            override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean =
                oldItem.mbid == newItem.mbid

            override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean =
                oldItem == newItem

        }
    }

    inner class ItemTrack(private val binding: ItemTrackBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(track: Track) {
            binding.track = track
            binding.duration = SimpleDateFormat("mm:ss:SSS").format(Date(track.duration))
            track.image.last().text.let {
                Glide
                    .with(itemView.context)
                    .load(it)
                    .override(500, 500)
                    .centerCrop()
                    .optionalCenterCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(binding.ivArtist)
            }
        }
    }

}