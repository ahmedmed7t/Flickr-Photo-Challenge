package com.blackstoneeit.flickrphotochallenge.app

import android.app.Application
import com.blackstoneeit.flickrphotochallenge.flickerImageList.di.appModule
import com.blackstoneeit.flickrphotochallenge.flickerImageList.di.photoListModule
import org.koin.core.context.GlobalContext.startKoin


class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(appModule, photoListModule))
        }
    }
}