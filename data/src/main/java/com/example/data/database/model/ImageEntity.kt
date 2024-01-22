package com.example.data.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(

    @PrimaryKey val id: Int = 0,
    val description: String,
    val url: String,
    val previewURL: String,
    val data: String

)
