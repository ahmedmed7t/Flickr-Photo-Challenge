package com.blackstoneeit.flickrphotochallenge.app.roomDB

import androidx.room.*
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel

@Dao
interface PhotoDao {

    @Query("SELECT * FROM PhotoModel")
    suspend fun getAll(): List<PhotoModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photos: PhotoModel)

    @Delete
    suspend fun delete(photos: PhotoModel)

    @Query("SELECT EXISTS(SELECT * FROM PhotoModel WHERE id = :id)")
    suspend fun isPhotoExist(id : String) : Boolean
}