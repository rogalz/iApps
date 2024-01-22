package com.example.data.repo

import com.example.data.database.model.ImageEntity
import kotlinx.coroutines.flow.Flow

interface ImageRepository {

    suspend fun fetchImages(): Result<List<ImageEntity>>
    suspend fun getImages(): Flow<List<ImageEntity>>
}