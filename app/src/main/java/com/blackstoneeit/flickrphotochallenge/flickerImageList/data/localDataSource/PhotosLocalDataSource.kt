package com.blackstoneeit.flickrphotochallenge.flickerImageList.data.localDataSource

import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel

interface PhotosLocalDataSource {
    suspend fun loadPhotos():List<PhotoModel>
    suspend fun savePhoto(photoModel: PhotoModel)
    suspend fun deletePhoto(photoModel: PhotoModel)
}