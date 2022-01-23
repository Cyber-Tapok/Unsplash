package com.tapok.core.mapper

import com.tapok.core.BlurHashMapper
import com.tapok.core.Mapper
import com.tapok.core.PhotoUrl
import com.tapok.unsplash.api.model.PhotoResponse
import javax.inject.Inject

class PhotoUrlMapper @Inject constructor(
    private val blurHashMapper: BlurHashMapper,
) : Mapper<PhotoResponse, PhotoUrl> {

    var type: PhotoSize = PhotoSize.SMALL
        private set

    fun photoSize(type: PhotoSize) = apply { this.type = type }

    override fun transform(data: PhotoResponse): PhotoUrl {
        val placeholder = blurHashMapper.transform(blurHash = data.blurHash, height = data.height, wight = data.width)
        return when (type) {
            PhotoSize.SMALL -> PhotoUrl.SmallPhoto(
                id = data.id,
                url = data.photoUrls.small,
                placeholder = placeholder
            )
            PhotoSize.REGULAR -> PhotoUrl.RegularPhoto(
                id = data.id,
                url = data.photoUrls.regular,
                placeholder = placeholder
            )
            PhotoSize.FULL -> PhotoUrl.FullPhoto(
                id = data.id,
                url = data.photoUrls.regular,
                placeholder = placeholder
            )
        }
    }
}


enum class PhotoSize {
    SMALL, REGULAR, FULL
}
