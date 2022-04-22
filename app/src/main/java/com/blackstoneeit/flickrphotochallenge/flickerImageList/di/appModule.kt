package com.blackstoneeit.flickrphotochallenge.flickerImageList.di

import android.content.Context
import androidx.room.Room
import com.blackstoneeit.flickrphotochallenge.BuildConfig.BASE_URL
import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.api.PhotosApiService
import com.blackstoneeit.flickrphotochallenge.flickerImageList.data.roomDB.AppDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), BASE_URL) }
    single { provideApiService(get()) }
    single { provideRoomDB(androidContext()) }
}

private fun provideOkHttpClient(): OkHttpClient = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
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

private fun provideRoomDB(context: Context) =
    Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        "flicker-photos"
    ).build()