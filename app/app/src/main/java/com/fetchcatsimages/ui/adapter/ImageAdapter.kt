package com.fetchcatsimages.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fetchcatsimages.databinding.AdapterItemImageBinding
import com.fetchcatsimages.model.Image

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var items: List<Image> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterItemImageBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = items[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<Image>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ImageViewHolder internal constructor(private val binding: AdapterItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(img: Image) {
            binding.apply {
                Glide.with(itemView.context).load(img.imageUrl).into(imageView)
            }
        }
    }

}