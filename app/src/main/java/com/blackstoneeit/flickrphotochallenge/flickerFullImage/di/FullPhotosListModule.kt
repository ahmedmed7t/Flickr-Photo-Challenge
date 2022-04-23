package com.blackstoneeit.flickrphotochallenge.flickerFullImage.di

import com.blackstoneeit.flickrphotochallenge.flickerFullImage.data.repository.FullPhotoRepositoryImp
import com.blackstoneeit.flickrphotochallenge.flickerFullImage.domain.repository.FullPhotoRepository
import com.blackstoneeit.flickrphotochallenge.flickerFullImage.presentation.FullPhotoViewModel
import com.blackstoneeit.flickrphotochallenge.flickerFullImage.data.localDataSource.FullPhotoLocalDataSource
import com.blackstoneeit.flickrphotochallenge.flickerFullImage.data.localDataSource.FullPhotoLocalDataSourceImp
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