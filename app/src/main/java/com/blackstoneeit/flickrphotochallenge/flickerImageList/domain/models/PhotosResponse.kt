package com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class PhotosResponse(
    val page: Int,
    val pages: Long,
    @SerializedName("perpage")
    val perPage: Int,
    val total: Long,
    @SerializedName("photo")
    val photoList: ArrayList<PhotoModel>,
    @SerializedName("stat")
    val status: String
)