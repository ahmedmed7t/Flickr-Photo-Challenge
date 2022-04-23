package com.blackstoneeit.flickrphotochallenge.app

import android.app.Application
import com.blackstoneeit.flickrphotochallenge.app.di.appModule
import com.blackstoneeit.flickrphotochallenge.flickerFullImage.di.fullPhotoListModule
import com.blackstoneeit.flickrphotochallenge.flickerImageList.di.photoListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, photoListModule, fullPhotoListModule))
        }
    }
}