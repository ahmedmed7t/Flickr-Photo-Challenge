package com.blackstoneeit.flickrphotochallenge.flickerImageList.data.localDataSource

import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.roomDB.AppDatabase
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel

class PhotosLocalDataSourceImp(private val appDatabase: AppDatabase): PhotosLocalDataSource {
    override suspend fun loadPhotos(): List<PhotoModel> {
        return appDatabase.PhotoDao().getAll()
    }

    override suspend fun savePhoto(photoModel: PhotoModel) =
        appDatabase.PhotoDao().insertPhoto(photoModel)

    override suspend fun deletePhoto(photoModel: PhotoModel) =
        appDatabase.PhotoDao().delete(photoModel)
}