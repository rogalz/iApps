package com.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.database.model.ImageEntity

@Dao
interface ImageDao {

    @Query("SELECT * FROM imageentity")
    suspend fun getAll(): List<ImageEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllImages(images: List<ImageEntity>)

}
