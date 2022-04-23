package com.blackstoneeit.flickrphotochallenge.flickerFullImage.domain.repository

import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotosResponseModel
import retrofit2.Response

interface FullPhotoRepository {
    suspend fun savePhotoModel(photoModel: PhotoModel)
    suspend fun deletePhoto(photoModel: PhotoModel)
    suspend fun isPhotoExist(id: String): Boolean
}