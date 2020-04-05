package com.alexander.appvalid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexander.appvalid.databinding.ItemTestBinding
import com.alexander.appvalid.models.Artist

class TestAdapter : PagedListAdapter<Artist, TestAdapter.ItemTest>(ARTIST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTest {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTestBinding.inflate(inflater)
        return ItemTest(binding)
    }

    override fun onBindViewHolder(holder: ItemTest, position: Int) {
        val item = getItem(position)
        item?.let { it -> holder.bind(it) }
    }

    companion object {
        private val ARTIST_COMPARATOR = object : DiffUtil.ItemCallback<Artist>() {
            override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean =
                oldItem.mbid == newItem.mbid

            override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean =
                oldItem == newItem

        }
    }

    inner class ItemTest(private val binding: ItemTestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(artist: Artist) {
            binding.artist = artist
        }
    }
}