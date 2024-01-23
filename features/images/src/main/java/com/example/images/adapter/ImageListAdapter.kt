package com.example.images.adapter

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.images.databinding.ItemImageBinding
import com.example.images.model.ImageItem

class ImageListAdapter(private val onClickListener: (String) -> Unit) : ListAdapter<ImageItem, ImageListAdapter.ImageViewHolder>(ImagesDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image)
    }

    class ImageViewHolder(private val binding: ItemImageBinding, private val onClickListener: (String) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: ImageItem) {

            with(binding) {
                imagePreview.load(image.previewURL)
                if (image.description.isBlank()) {
                    description.visibility = GONE
                } else {
                    description.visibility = VISIBLE
                    description.text = image.description
                }
                date.text = image.date

                itemBackground.setOnClickListener { onClickListener.invoke(image.url) }
            }
        }
    }
}
