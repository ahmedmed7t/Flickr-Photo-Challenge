package com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models

import com.google.gson.annotations.SerializedName

data class PhotoModel(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Long,
    val title: String,
    @SerializedName("ispublic")
    val isPublic: Int,
    @SerializedName("isfriend")
    val isFriend: Int,
    @SerializedName("isfamily")
    val isFamily: Int
)
