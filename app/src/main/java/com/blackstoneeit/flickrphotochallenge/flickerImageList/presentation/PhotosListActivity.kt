package com.blackstoneeit.flickrphotochallenge.flickerImageList.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blackstoneeit.flickrphotochallenge.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosListActivity : AppCompatActivity() {
    private val viewModel: PhotosListViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}