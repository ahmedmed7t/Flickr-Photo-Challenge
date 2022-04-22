package com.blackstoneeit.flickrphotochallenge.flickerImageList.data.roomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel

@Database(entities = [PhotoModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun PhotoDao(): PhotoDao
}