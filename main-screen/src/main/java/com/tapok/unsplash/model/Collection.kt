package com.tapok.unsplash.model

import com.tapok.core.PhotoUrl

internal data class Collection(
    val id: String,
    val title: String,
    val cover: PhotoUrl,
    val size: Int,
)