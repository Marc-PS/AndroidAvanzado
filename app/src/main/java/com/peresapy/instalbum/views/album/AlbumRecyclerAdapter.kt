package com.peresapy.instalbum.views.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.peresapy.instalbum.databinding.AlbumItemLayoutBinding
import com.peresapy.instalbum.gallery.AlbumImage
import com.peresapy.instalbum.load
import timber.log.Timber
import kotlin.math.log

class AlbumRecyclerAdapter : RecyclerView.Adapter<AlbumItemViewHolder>() {

    var images: List<AlbumImage> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumItemViewHolder =
        AlbumItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { AlbumItemViewHolder(this) }

    override fun onBindViewHolder(holder: AlbumItemViewHolder, position: Int) {
        holder.bind(image = images[position])
    }

    override fun getItemCount(): Int = images.count()
}


class AlbumItemViewHolder(
    private val binding: AlbumItemLayoutBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(image: AlbumImage) {
        with(binding) {
            albumItemImageView.setImageBitmap(null)
            albumItemImageView.load(image.link)
        }
    }
}