package com.blackstoneeit.flickrphotochallenge.flickerFullImage.data.repository

import com.blackstoneeit.flickrphotochallenge.flickerFullImage.domain.repository.FullPhotoRepository
import com.blackstoneeit.flickrphotochallenge.flickerFullImage.data.localDataSource.FullPhotoLocalDataSource
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel

class FullPhotoRepositoryImp(
    private val fullPhotoLocalDataSource: FullPhotoLocalDataSource
) : FullPhotoRepository {
    override suspend fun savePhotoModel(photoModel: PhotoModel) =
        fullPhotoLocalDataSource.savePhoto(photoModel)

    override suspend fun deletePhoto(photoModel: PhotoModel) =
        fullPhotoLocalDataSource.deletePhoto(photoModel)

    override suspend fun isPhotoExist(id: String) =
        fullPhotoLocalDataSource.isPhotoExist(id)
}