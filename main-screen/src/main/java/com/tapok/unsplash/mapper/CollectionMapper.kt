package com.tapok.unsplash.mapper

import com.tapok.core.Mapper
import com.tapok.core.mapper.PhotoUrlMapper
import com.tapok.unsplash.api.model.CollectionResponse
import com.tapok.unsplash.model.Collection
import javax.inject.Inject

internal class CollectionMapper @Inject constructor(
    private val photoUrlMapper: PhotoUrlMapper
) : Mapper<CollectionResponse, Collection> {

    override fun transform(data: CollectionResponse): Collection {
        return Collection(
            id = data.id,
            title = data.title,
            cover = photoUrlMapper.transform(data.coverPhoto),
            size = data.totalPhotos
        )
    }
}