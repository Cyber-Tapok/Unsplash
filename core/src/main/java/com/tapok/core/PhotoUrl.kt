package com.tapok.core

import android.graphics.drawable.Drawable

sealed class PhotoUrl {
    abstract val id: String
    abstract val url: String
    abstract val placeholder: Drawable

    data class SmallPhoto(override val id: String, override val url: String, override val placeholder: Drawable) : PhotoUrl()

    data class RegularPhoto(override val id: String, override val url: String, override val placeholder: Drawable) : PhotoUrl()

    data class FullPhoto(override val id: String, override val url: String, override val placeholder: Drawable) : PhotoUrl()
}
