package com.blackstoneeit.flickrphotochallenge.flickerImageList.di

import com.blackstoneeit.flickrphotochallenge.utils.NetworkHelper
import com.blackstoneeit.flickrphotochallenge.BuildConfig.BASE_URL
import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.api.PhotosApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), BASE_URL) }
    single { provideApiService(get()) }
    single { provideNetworkHelper() }
}

private fun provideNetworkHelper(): NetworkHelper = NetworkHelper()

private fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): PhotosApiService =
    retrofit.create(PhotosApiService::class.java)