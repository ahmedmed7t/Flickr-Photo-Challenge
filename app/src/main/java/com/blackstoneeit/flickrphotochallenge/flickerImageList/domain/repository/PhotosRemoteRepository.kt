package com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.repository

interface PhotosRemoteRepository {
    suspend fun loadPhotosList()
}