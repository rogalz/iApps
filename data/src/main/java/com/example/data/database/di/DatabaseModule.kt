package com.example.data.database.di

import android.content.Context
import androidx.room.Room
import com.example.data.database.ImageDao
import com.example.data.database.ImageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context): ImageDatabase {
        return Room.databaseBuilder(
            appContext,
            ImageDatabase::class.java,
            "db"
        ).build()
    }

    @Provides
    fun provideImageDao(appDatabase: ImageDatabase): ImageDao {
        return appDatabase.imageDao()
    }

}
