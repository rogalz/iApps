package com.example.images.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.images.model.ImageItem

class ImagesDiffUtil : DiffUtil.ItemCallback<ImageItem>() {

    override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem) = oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem) = oldItem == newItem
}
