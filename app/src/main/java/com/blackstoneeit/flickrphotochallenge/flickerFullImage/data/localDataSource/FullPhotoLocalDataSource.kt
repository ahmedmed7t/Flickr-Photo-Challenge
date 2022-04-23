package com.blackstoneeit.flickrphotochallenge.flickerFullImage.data.localDataSource

import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel

interface FullPhotoLocalDataSource {
    suspend fun savePhoto(photoModel: PhotoModel)
    suspend fun deletePhoto(photoModel: PhotoModel)
    suspend fun isPhotoExist(id: String): Boolean
}