package com.blackstoneeit.flickrphotochallenge.flickerFullImage.domain.repository

import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel

interface FullPhotoRepository {
    suspend fun savePhotoModel(photoModel: PhotoModel)
    suspend fun deletePhoto(photoModel: PhotoModel)
    suspend fun isPhotoExist(id: String): Boolean
}