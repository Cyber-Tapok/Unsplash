package com.tapok.unsplash.api.model

import com.google.gson.annotations.SerializedName

data class LinksResponse(
    @SerializedName("html") val webLink: String,
)
