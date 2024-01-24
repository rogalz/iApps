package com.example.data.repo

import com.example.data.api.ImagesApiService
import com.example.data.database.ImageDao
import com.example.data.database.model.ImageEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class ImageRepositoryImpl @Inject constructor(
    private val apiService: ImagesApiService,
    private val imageDao: ImageDao,
) : ImageRepository {

    override suspend fun getImages(): Flow<List<ImageEntity>> = flow {
        emit(tryToGetImages())
        emit(fetchImages())

    }.distinctUntilChanged()

    private suspend fun tryToGetImages(): List<ImageEntity> {
        imageDao.getAll()?.let {
            if (it.isNotEmpty()) {
                return it
            }
        }
        return emptyList()
    }

    private suspend fun fetchImages(): List<ImageEntity> {
        val response = apiService.getImages("cat")

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
                return this
            }
        }
        return emptyList()
    }
}
