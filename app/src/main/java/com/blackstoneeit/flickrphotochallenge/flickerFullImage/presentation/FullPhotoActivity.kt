package com.blackstoneeit.flickrphotochallenge.flickerFullImage.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.blackstoneeit.flickrphotochallenge.R
import com.blackstoneeit.flickrphotochallenge.databinding.ActivityFullPhotoBinding
import com.blackstoneeit.flickrphotochallenge.flickerFullImage.presentation.handler.FullPhotoHandler
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel
import com.blackstoneeit.flickrphotochallenge.utils.FlickerImageUtils
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class FullPhotoActivity : AppCompatActivity(), FullPhotoHandler {
    private val viewModel: FullPhotoViewModel by viewModel()
    private lateinit var binding: ActivityFullPhotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.callback = this

        getExtraData()
        viewModel.checkIsPhotoExist()
        listenToViewModelValues()
    }

    private fun getExtraData() {
        val fullPhoto = intent.getParcelableExtra<PhotoModel>(FULL_PHOTO_KEY)
        viewModel.setFullPhoto(fullPhoto)
        showFullImage(fullPhoto)
    }

    private fun showFullImage(photoModel: PhotoModel?) =
        photoModel?.let { photoModel ->
            Glide.with(this)
                .load(FlickerImageUtils.getImageUrl(photoModel))
                .into(binding.fullPhotoImageView)
        }


    private fun listenToViewModelValues() {
        viewModel.isPhotoExist.observe(this) {
            if (it) {
                binding.fullPhotoActionImage.setImageResource(R.drawable.ic_baseline_trash_24)
            } else {
                binding.fullPhotoActionImage.setImageResource(R.drawable.ic_baseline_arrow_downward_24)
            }
        }

        viewModel.message.observe(this) { message ->
            if (!message.isNullOrBlank()) {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onBackClick(view: View) =
        onBackPressed()

    override fun onActionClick(view: View) =
        viewModel.photoActionClicked()

    companion object {
        const val FULL_PHOTO_KEY = "FULL_PHOTO"
    }
}