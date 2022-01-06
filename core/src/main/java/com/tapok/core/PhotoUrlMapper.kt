package com.tapok.core

import com.tapok.unsplash.api.model.PhotoResponse
import javax.inject.Inject

class PhotoUrlMapper @Inject constructor(): Mapper<PhotoResponse, PhotoUrl> {
    var type: PhotoSize = PhotoSize.SMALL
        private set

    fun photoSize(type: PhotoSize) = apply { this.type = type }

    override fun transform(data: PhotoResponse): PhotoUrl {
        return when (type) {
            PhotoSize.SMALL -> PhotoUrl.SmallPhoto(data.photoUrls.small, blurHash = data.blurHash)
            PhotoSize.REGULAR -> PhotoUrl.RegularPhoto(data.photoUrls.regular, blurHash = data.blurHash)
            PhotoSize.FULL -> PhotoUrl.FullPhoto(data.photoUrls.regular, blurHash = data.blurHash)
        }
    }
}

enum class PhotoSize {
    SMALL, REGULAR, FULL
}