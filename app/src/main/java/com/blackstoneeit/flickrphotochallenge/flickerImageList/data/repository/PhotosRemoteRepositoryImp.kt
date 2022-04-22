package com.blackstoneeit.flickrphotochallenge.flickerImageList.data.repository

import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.remoteDataSource.PhotosRemoteDataSource
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.repository.PhotosRemoteRepository

class PhotosRemoteRepositoryImp(
    private val photosRemoteDataSource: PhotosRemoteDataSource
): PhotosRemoteRepository{
    override suspend fun loadPhotosList(page: Int) =
        photosRemoteDataSource.loadPhotos(page)

}