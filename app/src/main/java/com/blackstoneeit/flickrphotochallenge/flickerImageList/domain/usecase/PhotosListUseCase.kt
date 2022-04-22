package com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.usecase

import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.repository.PhotosRemoteRepository

class PhotosListUseCase(
    private val photosRemoteRepository: PhotosRemoteRepository
) {
    suspend fun loadPhotos(page: Int) =
        photosRemoteRepository.loadPhotosList(page)
}