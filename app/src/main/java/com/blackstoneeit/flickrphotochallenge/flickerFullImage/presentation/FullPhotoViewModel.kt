package com.blackstoneeit.flickrphotochallenge.flickerFullImage.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blackstoneeit.flickrphotochallenge.flickerFullImage.domain.repository.FullPhotoRepository
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel
import kotlinx.coroutines.launch

class FullPhotoViewModel(
    private val fullPhotoRepository: FullPhotoRepository
) : ViewModel() {

    private val _isPhotoExist = MutableLiveData<Boolean>()
    val isPhotoExist: LiveData<Boolean>
        get() = _isPhotoExist

    private val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

    private var fullPhoto: PhotoModel? = null

    fun checkIsPhotoExist() {
        viewModelScope.launch {
            fullPhoto?.let { photo ->
                val isExist = fullPhotoRepository.isPhotoExist(photo.id)
                _isPhotoExist.postValue(isExist)
            }
        }
    }

    fun photoActionClicked() {
        fullPhoto?.let { photo ->
            viewModelScope.launch {
                if (_isPhotoExist.value == true) {
                    fullPhotoRepository.deletePhoto(photo)
                    _message.postValue("deleted")
                    _isPhotoExist.postValue(false)
                } else {
                    fullPhotoRepository.savePhotoModel(photo)
                    _message.postValue("saved")
                    _isPhotoExist.postValue(true)
                }
            }
        }
    }

    fun setFullPhoto(fullPhoto: PhotoModel?) {
        this.fullPhoto = fullPhoto
    }
}