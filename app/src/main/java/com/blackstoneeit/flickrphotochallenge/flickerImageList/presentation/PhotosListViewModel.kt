package com.blackstoneeit.flickrphotochallenge.flickerImageList.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.repository.PhotosRepository
import com.blackstoneeit.flickrphotochallenge.utils.notifyObserver
import kotlinx.coroutines.launch

class PhotosListViewModel(
    private val photosListRepository: PhotosRepository
) : ViewModel() {

    private val _photos = MutableLiveData<ArrayList<PhotoModel>>()
    val photos: LiveData<ArrayList<PhotoModel>>
        get() = _photos

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private var canLoadMore = true
    private var isLoading = false

    private var page = 0

    fun loadPhotos() {
        if(!isLoading && canLoadMore) {
            page++
            isLoading = true
            viewModelScope.launch {
                photosListRepository.loadPhotosList(page).let {
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
                        _message.postValue(it.message())
                    }
                }
            }
        }else{
            _message.postValue("")
        }
    }

    fun getSavedPhotos(){
        viewModelScope.launch {
            _photos.postValue(photosListRepository.loadSavedPhotos().toCollection(ArrayList()))
        }
    }

    fun photoActionClicked(position: Int, screenMode: Int){
        if(screenMode == PhotosListActivity.GLOBAL_MODE){
            savePhoto(position)
        }else{
            deletePhoto(position)
        }
    }

    private fun savePhoto(position: Int){
        viewModelScope.launch {
            _photos.value?.get(position)?.let { photosListRepository.savePhotoModel(it) }
            _message.postValue("Saved")
        }
    }

    private fun deletePhoto(position: Int){
        viewModelScope.launch {
            _photos.value?.get(position)?.let { photosListRepository.deletePhoto(it) }
            _photos.value?.remove(_photos.value?.get(position))
            _photos.notifyObserver()
            _message.postValue("deleted")
        }
    }

    fun clearPhotos(){
        _photos.postValue(arrayListOf())
    }
}