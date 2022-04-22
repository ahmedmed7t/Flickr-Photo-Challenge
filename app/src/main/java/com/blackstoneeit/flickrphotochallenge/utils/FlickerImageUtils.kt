package com.blackstoneeit.flickrphotochallenge.utils

import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel

object FlickerImageUtils {
    fun getImageUrl(photoModel: PhotoModel) =
        "http://farm${photoModel.farm}.static.flickr.com/${photoModel.server}/${photoModel.id}_${photoModel.secret}.jpg"
}