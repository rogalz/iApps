package com.example.images.model

import com.example.data.database.model.ImageEntity

data class ImageListState(
    val error: Boolean = false,
    val loading: Boolean = true,
    val list: List<ImageEntity> = emptyList(),
)
