package com.blackstoneeit.flickrphotochallenge.flickerImageList.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blackstoneeit.flickrphotochallenge.R
import com.blackstoneeit.flickrphotochallenge.databinding.AdItemBinding
import com.blackstoneeit.flickrphotochallenge.databinding.PostItemBinding
import com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models.PhotoModel
import com.blackstoneeit.flickrphotochallenge.flickerImageList.presentation.handler.PhotoListClickListener
import com.blackstoneeit.flickrphotochallenge.utils.FlickerImageUtils
import com.bumptech.glide.Glide

class PhotoListAdapter(
    private var photoList: ArrayList<PhotoModel>,
    private val clickListener: PhotoListClickListener,
    private var screenMode: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val POST_TYPE = 1
    private val AD_TYPE = 0

    private val adPosition = 5

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == AD_TYPE) {
            val binding = AdItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            AdViewHolder(binding)
        } else {
            val binding =
                PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            PostViewHolder(binding)
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == AD_TYPE) {
            holder as AdViewHolder
            Glide.with(holder.itemView.context)
                .load(FlickerImageUtils.getImageUrl(photoList[position]))
                .into(holder.binding.adContentImageView)
        } else {
            holder as PostViewHolder
            holder.binding.photoDownloadImage.apply {
                if (screenMode == PhotosListActivity.GLOBAL_MODE) {
                    setImageResource(R.drawable.ic_baseline_arrow_downward_24)
                } else {
                    setImageResource(R.drawable.ic_baseline_trash_24)
                }
            }

            holder.binding.apply {
                Glide.with(holder.itemView.context)
                    .load(FlickerImageUtils.getImageUrl(photoList[position]))
                    .into(photoImage)
                photoTitleTextView.text = photoList[position].title
                photoDownloadImage.setOnClickListener {
                    clickListener.onActionClick(position)
                }
                photoCardContainer.setOnClickListener {
                    clickListener.onClick(position)
                }
            }
        }
    }

    override fun getItemCount() =
        photoList.size

    override fun getItemViewType(position: Int) =
        if (position != 0 && position % adPosition == 0) {
            AD_TYPE
        } else {
            POST_TYPE
        }

    inner class PostViewHolder(val binding: PostItemBinding) : RecyclerView.ViewHolder(binding.root)

    inner class AdViewHolder(val binding: AdItemBinding) : RecyclerView.ViewHolder(binding.root)

    fun setPhotos(photos: ArrayList<PhotoModel>, screenMode: Int) {
        photoList = photos
        this.screenMode = screenMode
        notifyDataSetChanged()
    }
}