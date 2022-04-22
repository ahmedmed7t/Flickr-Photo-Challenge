package com.blackstoneeit.flickrphotochallenge.flickerImageList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.usecase.PhotosListUseCase
import com.blackstoneeit.flickrphotochallenge.utils.notifyObserver
import kotlinx.coroutines.launch

class PhotosListViewModel(
    private val photosListUseCase: PhotosListUseCase
) : ViewModel() {

    private val _photos = MutableLiveData<ArrayList<PhotoModel>>()
    val photos: LiveData<ArrayList<PhotoModel>>
        get() = _photos

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    private var canLoadMore = true
    private var isLoading = false

    private var page = 0

    fun loadPhotos() {
        if(!isLoading && canLoadMore) {
            page++
            isLoading = true
            viewModelScope.launch {
                photosListUseCase.loadPhotos(page).let {
                    isLoading = false
                    if (it.isSuccessful) {
                        if(_photos.value == null)
                            _photos.postValue(it.body()?.photos?.photoList ?: arrayListOf())
                        else {
                            _photos.value?.addAll(it.body()?.photos?.photoList ?: arrayListOf())
                            _photos.notifyObserver()
                        }

                        if(_photos.value?.size?.toLong() == it.body()?.photos?.total)
                            canLoadMore = false
                    } else {
                        _errorMessage.postValue(it.message())
                    }
                }
            }
        }else{
            _errorMessage.postValue("")
        }
    }
}