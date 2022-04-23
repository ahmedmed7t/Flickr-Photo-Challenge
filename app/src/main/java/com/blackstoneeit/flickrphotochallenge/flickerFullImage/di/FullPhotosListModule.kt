package com.blackstoneeit.flickrphotochallenge.flickerFullImage.di

import com.blackstoneeit.flickrphotochallenge.flickerFullImage.data.repository.FullPhotoRepositoryImp
import com.blackstoneeit.flickrphotochallenge.flickerFullImage.domain.repository.FullPhotoRepository
import com.blackstoneeit.flickrphotochallenge.flickerFullImage.presentation.FullPhotoViewModel
import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.localDataSource.FullPhotoLocalDataSource
import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.localDataSource.FullPhotoLocalDataSourceImp
import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.remoteDataSource.PhotosRemoteDataSource
import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.remoteDataSource.PhotosRemoteDataSourceImp
import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.repository.PhotosRepositoryImp
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.repository.PhotosRepository
import com.blackstoneeit.flickrphotochallenge.flickerImageList.presentation.PhotosListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fullPhotoListModule = module {
    single<FullPhotoLocalDataSource> {
        return@single FullPhotoLocalDataSourceImp(get())
    }

    single<FullPhotoRepository> {
        return@single FullPhotoRepositoryImp(get())
    }

    viewModel {
        return@viewModel FullPhotoViewModel(get())
    }
}