package com.blackstoneeit.flickrphotochallenge.flickerImageList.data.remoteDataSource

import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.api.PhotosApiService

class PhotosRemoteDataSourceImp(private val photosApiService: PhotosApiService) :
    PhotosRemoteDataSource {
    override suspend fun loadPhotos() {
        photosApiService.loadPhotosList(1)
    }
}