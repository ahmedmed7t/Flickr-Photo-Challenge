package com.blackstoneeit.flickrphotochallenge.flickerImageList.data.remoteDataSource

interface PhotosRemoteDataSource {
    suspend fun loadPhotos()
}