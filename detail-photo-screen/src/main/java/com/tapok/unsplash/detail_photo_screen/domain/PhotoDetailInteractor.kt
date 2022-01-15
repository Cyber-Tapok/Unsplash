package com.tapok.unsplash.detail_photo_screen.domain

import com.tapok.unsplash.detail_photo_screen.model.Photo
import com.tapok.unsplash.detail_photo_screen.model.PhotoDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class PhotoDetailInteractor @Inject constructor(private val photoDetailRepository: PhotoDetailRepository) {

    fun getPhoto(photoId: String): Flow<Photo> = photoDetailRepository.getPhoto(photoId)
}