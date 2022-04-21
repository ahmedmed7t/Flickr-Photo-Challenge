package com.blackstoneeit.flickrphotochallenge.flickerImageList.di

import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.remoteDataSource.PhotosRemoteDataSource
import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.remoteDataSource.PhotosRemoteDataSourceImp
import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.repository.PhotosRemoteRepositoryImp
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.repository.PhotosRemoteRepository
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.usecase.PhotosListUseCase
import com.blackstoneeit.flickrphotochallenge.flickerImageList.presentation.PhotosListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val photoListModule = module {
    single<PhotosRemoteDataSource> {
        return@single PhotosRemoteDataSourceImp(get())
    }

    single<PhotosRemoteRepository> {
        return@single PhotosRemoteRepositoryImp(get())
    }

    factory {
        return@factory PhotosListUseCase(get())
    }

    viewModel {
        return@viewModel PhotosListViewModel(get())
    }
}