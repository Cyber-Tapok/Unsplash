package com.tapok.core

import com.tapok.unsplash.api.model.PhotoResponse

sealed class PhotoUrl {
    abstract val url: String
    abstract val blurHash: String

    data class SmallPhoto(override val url: String, override val blurHash: String): PhotoUrl()

    data class RegularPhoto(override val url: String, override val blurHash: String): PhotoUrl()

    data class FullPhoto(override val url: String, override val blurHash: String): PhotoUrl()
}
