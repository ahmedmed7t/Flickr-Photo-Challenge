package com.blackstoneeit.flickrphotochallenge.flickerImageList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.usecase.PhotosListUseCase
import kotlinx.coroutines.launch

class PhotosListViewModel(
    private val photosListUseCase: PhotosListUseCase
): ViewModel() {
    init {
       loadPhotos()
    }

    private fun loadPhotos(){
        viewModelScope.launch {
            photosListUseCase.loadPhotos()
        }
    }
}