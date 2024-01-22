package com.example.data.repo

import com.example.data.api.ImagesApiService
import com.example.data.database.ImageDao
import com.example.data.database.model.ImageEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ImageRepositoryImpl @Inject constructor(
    private val apiService: ImagesApiService,
    private val imageDao: ImageDao,
) : ImageRepository {

    override suspend fun fetchImages(): Result<List<ImageEntity>> {
        val response = apiService.getImages()

        if (response.isSuccessful) {
            response.body()?.items?.map {
                ImageEntity(
                    description = it.description ?: "",
                    url = it.link ?: "",
                    previewURL = it.media?.url ?: "",
                    data = it.dateTaken ?: "",
                )
            }.apply {
                this?.let {
                    imageDao.insertAllImages(it)
                    return Result.success(this)
                }
            }
        }

        return Result.failure(Throwable(response.message()))
    }

    override suspend fun getImages(): Flow<List<ImageEntity>> = imageDao.getAll()

}