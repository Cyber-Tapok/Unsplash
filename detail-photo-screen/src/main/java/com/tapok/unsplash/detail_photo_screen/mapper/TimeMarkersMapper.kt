package com.tapok.unsplash.detail_photo_screen.mapper

import com.cesarferreira.tempo.toDate
import com.tapok.core.Mapper
import com.tapok.unsplash.api.model.PhotoResponse
import com.tapok.unsplash.detail_photo_screen.model.TimeMarkers
import javax.inject.Inject

internal class TimeMarkersMapper @Inject constructor() : Mapper<PhotoResponse, TimeMarkers> {

    private val dateFormat = "yyyy-MM-dd'T'HH:mm:ssZ"

    override fun transform(data: PhotoResponse): TimeMarkers {
        return TimeMarkers(
            createdAt = data.createdAt.toDate(dateFormat),
            updatedAt = data.updatedAt?.toDate(dateFormat),
            promotedAt = data.promotedAt?.toDate(dateFormat),
        )
    }
}