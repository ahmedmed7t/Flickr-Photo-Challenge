package com.blackstoneeit.flickrphotochallenge.flickerImageList.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.blackstoneeit.flickrphotochallenge.R
import com.blackstoneeit.flickrphotochallenge.databinding.ActivityPhotosListBinding
import com.blackstoneeit.flickrphotochallenge.flickerImageList.presentation.handler.PhotoListClickListener
import com.blackstoneeit.flickrphotochallenge.flickerImageList.presentation.handler.ViewClickHandler
import com.blackstoneeit.flickrphotochallenge.utils.NetworkHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotosListActivity : AppCompatActivity(), PhotoListClickListener, ViewClickHandler {
    private val viewModel: PhotosListViewModel by viewModel()
    private lateinit var binding: ActivityPhotosListBinding

    private var currentMode = GLOBAL_MODE

    private val photoAdapter: PhotoListAdapter = PhotoListAdapter(arrayListOf(), this, currentMode)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotosListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.callback = this

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

    private fun getSavedPhotos() {
        showLoading()
        viewModel.getSavedPhotos()
    }

    private fun listenToViewModelValues() {
        viewModel.photos.observe(this) { photos ->
            hideLoading()
            photoAdapter.setPhotos(photos, currentMode)

            if (photos.isEmpty()) {
                showEmptyView()
            } else {
                showListView()
            }
        }

        viewModel.message.observe(this) { message ->
            hideLoading()
            if (!message.isNullOrBlank()) {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun listenToEndOfRecyclerView() {
        binding.photosRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && currentMode == GLOBAL_MODE) {
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

    private fun drawGlobalMode() {
        binding.photosFilterGlobalTextView.apply {
            setTextColor(
                ContextCompat.getColor(this@PhotosListActivity, R.color.white)
            )
            setBackgroundColor(
                ContextCompat.getColor(
                    this@PhotosListActivity,
                    R.color.design_default_color_primary
                )
            )
        }

        binding.photosFilterLocalTextView.apply {
            setTextColor(
                ContextCompat.getColor(this@PhotosListActivity, R.color.black)
            )
            setBackgroundColor(
                ContextCompat.getColor(this@PhotosListActivity, R.color.white)
            )
        }
    }

    override fun onGlobalFilterClick(view: View) {
        currentMode = GLOBAL_MODE
        drawGlobalMode()
        loadImages()
        viewModel.clearPhotos()
    }

    override fun onLocalFilterClick(view: View) {
        currentMode = LOCAL_MODE
        drawLocalMode()
        getSavedPhotos()
        viewModel.clearPhotos()
    }

    private fun drawLocalMode() {
        binding.photosFilterGlobalTextView.apply {
            setTextColor(
                ContextCompat.getColor(this@PhotosListActivity, R.color.black)
            )
            setBackgroundColor(
                ContextCompat.getColor(this@PhotosListActivity, R.color.white)
            )
        }

        binding.photosFilterLocalTextView.apply {
            setTextColor(
                ContextCompat.getColor(this@PhotosListActivity, R.color.white)
            )
            setBackgroundColor(
                ContextCompat.getColor(
                    this@PhotosListActivity,
                    R.color.design_default_color_primary
                )
            )
        }
    }

    override fun onClick(position: Int) =
        viewModel.photoActionClicked(position, currentMode)

    companion object {
        const val GLOBAL_MODE = 1
        const val LOCAL_MODE = 2
    }
}