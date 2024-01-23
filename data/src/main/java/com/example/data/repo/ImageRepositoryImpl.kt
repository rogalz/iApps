package com.example.data.repo

import com.example.data.api.ImagesApiService
import com.example.data.database.ImageDao
import com.example.data.database.model.ImageEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

internal class ImageRepositoryImpl @Inject constructor(
    private val apiService: ImagesApiService,
    private val imageDao: ImageDao,
) : ImageRepository {

    override suspend fun fetchImages(tags: String) {
        val response = apiService.getImages(tags)

        if (response.isSuccessful) {
            response.body()?.items?.map {
                ImageEntity(
                    description = it.description ?: "",
                    url = it.link ?: "",
                    previewURL = it.media?.url ?: "",
                    date = it.dateTaken ?: "",
                )
            }?.apply {
                imageDao.insertAllImages(this)
            }
        }
    }

    override suspend fun getImages(): Flow<List<ImageEntity>> = imageDao.getAll()

}