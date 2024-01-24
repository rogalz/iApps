package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.ImageDao
import com.example.data.database.model.ImageEntity

@Database(entities = [ImageEntity::class], version = 1)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao

}
