package com.blackstoneeit.flickrphotochallenge.flickerImageList.data.remoteDataSource

import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotosResponseModel
import retrofit2.Response

interface PhotosRemoteDataSource {
    suspend fun loadPhotos(page: Int): Response<PhotosResponseModel>
}