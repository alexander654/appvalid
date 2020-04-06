package com.alexander.appvalid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexander.appvalid.R
import com.alexander.appvalid.databinding.ItemArtistBinding
import com.alexander.appvalid.models.Artist
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class ArtistsAdapter : PagedListAdapter<Artist, ArtistsAdapter.ItemTest>(ARTIST_COMPARATOR) {

    var onItemClick: ((artist: Artist) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTest {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArtistBinding.inflate(inflater)
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

    inner class ItemTest(private val binding: ItemArtistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(artist: Artist) {
            binding.artist = artist
            artist.image.last().text.let {
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
            binding.btnArtist.setOnClickListener {
                onItemClick?.invoke(artist)
            }
        }
    }
}