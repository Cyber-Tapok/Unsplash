package com.tapok.unsplash.detail_photo_screen.model

import com.tapok.core.PhotoUrl
import java.util.*

internal data class Photo(
    val description: String?,
    val altDescription: String?,
    val photoSize: PhotoSize,
    val photo: PhotoUrl,
    val webLink: String,
    val timeMarkers: TimeMarkers,
    val author: Author,
    val statistic: PhotoStatistic,
)

internal data class PhotoStatistic(
    val likes: Int,
    val views: Int,
    val downloads: Int,
)

internal data class PhotoSize(
    val width: Int,
    val height: Int,
)

internal data class TimeMarkers(
    val createdAt: Date,
    val updatedAt: Date?,
    val promotedAt: Date?,
)

internal data class Author(
    val id: String,
    val username: String,
    val name: String,
    val profileImage: String,
)
