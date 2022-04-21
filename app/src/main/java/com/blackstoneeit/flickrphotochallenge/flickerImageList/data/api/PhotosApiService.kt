package com.blackstoneeit.flickrphotochallenge.flickerImageList.data.api

import retrofit2.http.GET
import retrofit2.http.Path

interface PhotosApiService {
    @GET
    suspend fun loadPhotosList(
        @Path("page_count", encoded = false)
        userId : Int
    )
}