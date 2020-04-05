package com.alexander.appvalid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexander.appvalid.databinding.ItemTrackBinding
import com.alexander.appvalid.models.Track

class TestAdapterTracks :
    PagedListAdapter<Track, TestAdapterTracks.ItemTestTrack>(TRACK_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTestTrack {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTrackBinding.inflate(inflater)
        return ItemTestTrack(binding)
    }

    override fun onBindViewHolder(holder: ItemTestTrack, position: Int) {
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

    inner class ItemTestTrack(private val binding: ItemTrackBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(track: Track) {
            binding.track = track
        }
    }

}