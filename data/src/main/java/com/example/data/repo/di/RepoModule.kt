package com.example.repo.di

import com.example.data.api.ImagesApiService
import com.example.data.database.ImageDao
import com.example.data.repo.ImageRepository
import com.example.data.repo.ImageRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepoModule {

    @Singleton
    @Provides
    fun providesRepository(apiService: ImagesApiService, imageDao: ImageDao): ImageRepository = ImageRepositoryImpl(apiService, imageDao)

}
