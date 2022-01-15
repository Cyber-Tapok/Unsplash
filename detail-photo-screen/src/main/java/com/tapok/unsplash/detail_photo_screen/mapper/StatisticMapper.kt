package com.tapok.unsplash.detail_photo_screen.mapper

import com.tapok.core.Mapper
import com.tapok.unsplash.api.model.PhotoResponse
import com.tapok.unsplash.detail_photo_screen.model.PhotoStatistic
import javax.inject.Inject

internal class StatisticMapper @Inject constructor() : Mapper<PhotoResponse, PhotoStatistic> {

    override fun transform(data: PhotoResponse): PhotoStatistic {
        return PhotoStatistic(
            likes = data.likes,
            views = data.views,
            downloads = data.downloads
        )
    }
}