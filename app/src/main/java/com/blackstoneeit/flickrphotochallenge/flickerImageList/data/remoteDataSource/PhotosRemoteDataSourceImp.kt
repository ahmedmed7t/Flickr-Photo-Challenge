package com.blackstoneeit.flickrphotochallenge.flickerImageList.data.remoteDataSource

import com.blackstoneeit.flickrphotochallenge.app.api.PhotosApiService
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotosResponseModel
import retrofit2.Response

class PhotosRemoteDataSourceImp(private val photosApiService: PhotosApiService) :
    PhotosRemoteDataSource {
    override suspend fun loadPhotos(page: Int): Response<PhotosResponseModel> {
        return photosApiService.loadPhotosList(page)
    }
}