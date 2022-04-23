package com.blackstoneeit.flickrphotochallenge.flickerImageList.data.repository

import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.localDataSource.PhotosLocalDataSource
import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.remoteDataSource.PhotosRemoteDataSource
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.repository.PhotosRepository

class PhotosRepositoryImp(
    private val photosRemoteDataSource: PhotosRemoteDataSource,
    private val photosLocalDataSource: PhotosLocalDataSource
) : PhotosRepository {
    override suspend fun loadPhotosList(page: Int) =
        photosRemoteDataSource.loadPhotos(page)

    override suspend fun loadSavedPhotos() =
        photosLocalDataSource.loadPhotos()

    override suspend fun savePhotoModel(photoModel: PhotoModel) =
        photosLocalDataSource.savePhoto(photoModel)

    override suspend fun deletePhoto(photoModel: PhotoModel) =
        photosLocalDataSource.deletePhoto(photoModel)
}