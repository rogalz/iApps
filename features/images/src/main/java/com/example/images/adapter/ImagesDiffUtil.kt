package com.example.images.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.images.model.ImageItem

class ImagesDiffUtil : DiffUtil.ItemCallback<ImageItem>() {

    override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem) = oldItem == newItem
}
