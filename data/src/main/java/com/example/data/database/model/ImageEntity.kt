package com.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    val description: String,
    @PrimaryKey
    val url: String,
    val previewURL: String,
    val date: String
)
