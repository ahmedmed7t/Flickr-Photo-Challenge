package com.blackstoneeit.flickrphotochallenge.flickerImageList.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.blackstoneeit.flickrphotochallenge.databinding.ActivityPhotosListBinding
import com.blackstoneeit.flickrphotochallenge.utils.NetworkHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosListActivity : AppCompatActivity() {
    private val viewModel: PhotosListViewModel by viewModel()
    private lateinit var binding: ActivityPhotosListBinding

    private val photoAdapter: PhotoListAdapter = PhotoListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotosListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        listenToViewModelValues()
        listenToEndOfRecyclerView()

        loadImages()
    }

    private fun initViews() =
        binding.photosRecyclerView.apply {
            setHasFixedSize(true)
            adapter = photoAdapter
        }

    private fun loadImages() =
        if (NetworkHelper.isNetworkConnected(this)) {
            showLoading()
            viewModel.loadPhotos()
        } else {
            showEmptyView()
        }


    private fun listenToViewModelValues() {
        viewModel.photos.observe(this) { photos ->
            hideLoading()
            if (photos.isNotEmpty()) {
                photoAdapter.setPhotos(photos)
            }
            if (photos.isEmpty()) {
                showEmptyView()
            } else {
                showListView()
            }
        }

        viewModel.errorMessage.observe(this) { errorMessage ->
            hideLoading()
            if (!errorMessage.isNullOrBlank()) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun listenToEndOfRecyclerView() {
        binding.photosRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    showLoading()
                    viewModel.loadPhotos()
                }
            }
        })
    }

    private fun showEmptyView() = binding.apply {
        photosRecyclerView.visibility = View.GONE
        photosEmptyImageView.visibility = View.VISIBLE
    }

    private fun showListView() = binding.apply {
        photosRecyclerView.visibility = View.VISIBLE
        photosEmptyImageView.visibility = View.GONE
    }

    private fun showLoading() {
        binding.photoListProgressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.photoListProgressBar.visibility = View.GONE
    }
}