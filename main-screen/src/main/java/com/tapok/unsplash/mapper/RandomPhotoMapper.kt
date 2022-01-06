package com.tapok.unsplash.mapper

import com.tapok.core.Mapper
import com.tapok.core.PhotoUrlMapper
import com.tapok.unsplash.api.model.PhotoResponse
import com.tapok.unsplash.model.RandomPhoto
import javax.inject.Inject

internal class RandomPhotoMapper @Inject constructor(
    private val photoUrlMapper: PhotoUrlMapper
) : Mapper<PhotoResponse, RandomPhoto> {

    override fun transform(data: PhotoResponse): RandomPhoto {
        return RandomPhoto(data.id, photoUrlMapper.transform(data))
    }
}