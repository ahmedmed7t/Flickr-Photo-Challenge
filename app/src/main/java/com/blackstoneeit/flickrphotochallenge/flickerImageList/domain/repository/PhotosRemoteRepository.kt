package com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.repository

import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotosResponseModel
import retrofit2.Response

interface PhotosRemoteRepository {
    suspend fun loadPhotosList(page: Int): Response<PhotosResponseModel>
}