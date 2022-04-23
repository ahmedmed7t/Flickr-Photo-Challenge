package com.blackstoneeit.flickrphotochallenge.app.api

import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotosResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApiService {
    //?method=flickr.photos.search&format=json&nojsoncallback=50&text=Color&per_page=20&api_key=d17378e37e555ebef55ab86c4180e8dc
    @GET(".")
    suspend fun loadPhotosList(
        @Query("page_count", encoded = false)
        pageCount : Int,
        @Query("method", encoded = false)
        method : String = "flickr.photos.search",
        @Query("format", encoded = false)
        format : String = "json",
        @Query("nojsoncallback", encoded = false)
        nojsoncallback : Int = 50,
        @Query("text", encoded = false)
        text : String = "Color",
        @Query("per_page", encoded = false)
        per_page : Int = 20,
        @Query("api_key", encoded = false)
        api_key : String = "d17378e37e555ebef55ab86c4180e8dc",

    ): Response<PhotosResponseModel>
}