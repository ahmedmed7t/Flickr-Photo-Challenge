package com.blackstoneeit.flickrphotochallenge.flickerImageList.di

import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.localDataSource.PhotosLocalDataSource
import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.localDataSource.PhotosLocalDataSourceImp
import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.remoteDataSource.PhotosRemoteDataSource
import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.remoteDataSource.PhotosRemoteDataSourceImp
import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.repository.PhotosRepositoryImp
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.repository.PhotosRepository
import com.blackstoneeit.flickrphotochallenge.flickerImageList.presentation.PhotosListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val photoListModule = module {
    single<PhotosRemoteDataSource> {
        return@single PhotosRemoteDataSourceImp(get())
    }

    single<PhotosLocalDataSource> {
        return@single PhotosLocalDataSourceImp(get())
    }

    single<PhotosRepository> {
        return@single PhotosRepositoryImp(get(), get())
    }

    viewModel {
        return@viewModel PhotosListViewModel(get())
    }
}