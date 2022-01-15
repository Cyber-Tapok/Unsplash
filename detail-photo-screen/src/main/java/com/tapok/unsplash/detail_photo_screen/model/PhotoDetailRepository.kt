package com.tapok.unsplash.detail_photo_screen.model

import com.tapok.unsplash.api.UnsplashService
import com.tapok.unsplash.detail_photo_screen.mapper.PhotoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal interface PhotoDetailRepository {
    fun getPhoto(photoId: String): Flow<Photo>
}

internal class PhotoDetailRepositoryImpl @Inject constructor(
    private val service: UnsplashService,
    private val photoMapper: PhotoMapper,
): PhotoDetailRepository {

    override fun getPhoto(photoId: String): Flow<Photo> = flow { emit(service.photo(photoId)) }
        .map(photoMapper::transform)
}