package com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.repository

import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotosResponseModel
import retrofit2.Response

interface PhotosRepository {
    suspend fun loadPhotosList(page: Int): Response<PhotosResponseModel>
    suspend fun loadSavedPhotos(): List<PhotoModel>
    suspend fun savePhotoModel(photoModel: PhotoModel)
    suspend fun deletePhoto(photoModel: PhotoModel)
}