package com.blackstoneeit.flickrphotochallenge.flickerImageList.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PhotoModel(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "owner")
    val owner: String,
    @ColumnInfo(name = "secret")
    val secret: String,
    @ColumnInfo(name = "server")
    val server: String,
    @ColumnInfo(name = "farm")
    val farm: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "isPublic")
    @SerializedName("ispublic")
    val isPublic: Int,
    @ColumnInfo(name = "isFriend")
    @SerializedName("isfriend")
    val isFriend: Int,
    @ColumnInfo(name = "isFamily")
    @SerializedName("isfamily")
    val isFamily: Int
)
