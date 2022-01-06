package com.tapok.unsplash.api.model

import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("id") val id: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String,
    @SerializedName("promoted_at") val promoted_at: String,
    @SerializedName("width") val width: Int,
    @SerializedName("height") val height: Int,
    @SerializedName("blur_hash") val blurHash: String,
    @SerializedName("description") val description: String,
    @SerializedName("alt_description") val altDescription: String,
    @SerializedName("urls") val photoUrls: PhotoUrlsResponse,
    @SerializedName("links") val links: LinksResponse,
    @SerializedName("likes") val likes: Int,
    @SerializedName("user") val user: UserResponse,
    @SerializedName("views") val views: Int,
    @SerializedName("downloads") val downloads: Int,

)

data class PhotoUrlsResponse(
    @SerializedName("full") val full: String,
    @SerializedName("regular") val regular: String,
    @SerializedName("small") val small: String,
)
