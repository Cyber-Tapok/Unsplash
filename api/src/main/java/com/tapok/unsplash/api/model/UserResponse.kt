package com.tapok.unsplash.api.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id") val id: String,
    @SerializedName("username") val username: String,
    @SerializedName("name") val name: String,
    @SerializedName("profile_image") val profileImage: ProfileImageResponse,

)

data class ProfileImageResponse(
    @SerializedName("medium") val medium: String,
)
