package com.blackstoneeit.flickrphotochallenge.flickerImageList.data.localDataSource

import com.blackstoneeit.flickrphotochallenge.app.roomDB.AppDatabase
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel

class FullPhotoLocalDataSourceImp(private val appDatabase: AppDatabase): FullPhotoLocalDataSource {

    override suspend fun savePhoto(photoModel: PhotoModel) =
        appDatabase.PhotoDao().insertPhoto(photoModel)

    override suspend fun deletePhoto(photoModel: PhotoModel) =
        appDatabase.PhotoDao().delete(photoModel)

    override suspend fun isPhotoExist(id: String) =
        appDatabase.PhotoDao().isPhotoExist(id)
}