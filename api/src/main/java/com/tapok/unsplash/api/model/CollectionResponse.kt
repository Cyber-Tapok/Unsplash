package com.tapok.unsplash.api.model

import com.google.gson.annotations.SerializedName

data class CollectionResponse(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("total_photos") val totalPhotos: Int,
    @SerializedName("user") val user: UserResponse,
    @SerializedName("cover_photo") val coverPhoto: PhotoResponse,
)
