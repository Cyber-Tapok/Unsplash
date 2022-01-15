package com.tapok.unsplash.detail_photo_screen.mapper

import com.tapok.core.Mapper
import com.tapok.core.PhotoUrlMapper
import com.tapok.unsplash.api.model.PhotoResponse
import com.tapok.unsplash.detail_photo_screen.model.Photo
import com.tapok.unsplash.detail_photo_screen.model.PhotoSize
import javax.inject.Inject

internal class PhotoMapper @Inject constructor(
    private val statisticMapper: StatisticMapper,
    private val authorMapper: AuthorMapper,
    private val timeMarkersMapper: TimeMarkersMapper,
    private val photoUrlMapper: PhotoUrlMapper,
) : Mapper<PhotoResponse, Photo> {

    override fun transform(data: PhotoResponse): Photo {
        val statistic = statisticMapper.transform(data)
        val author = authorMapper.transform(data)
        val timeMarkers = timeMarkersMapper.transform(data)
        val photoUrl = photoUrlMapper.photoSize(com.tapok.core.PhotoSize.FULL).transform(data)
        return Photo(
            description = data.description,
            altDescription = data.altDescription,
            photoSize = PhotoSize(data.width, data.height),
            photo = photoUrl,
            webLink = data.links.webLink,
            timeMarkers = timeMarkers,
            author = author,
            statistic = statistic
        )
    }
}