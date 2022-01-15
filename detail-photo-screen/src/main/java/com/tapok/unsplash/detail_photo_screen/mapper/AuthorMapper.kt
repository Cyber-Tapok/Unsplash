package com.tapok.unsplash.detail_photo_screen.mapper

import com.tapok.core.Mapper
import com.tapok.unsplash.api.model.PhotoResponse
import com.tapok.unsplash.detail_photo_screen.model.Author
import javax.inject.Inject

internal class AuthorMapper @Inject constructor() : Mapper<PhotoResponse, Author> {

    override fun transform(data: PhotoResponse): Author {
        return Author(
            id = data.user.id,
            username = data.user.username,
            name = data.user.name,
            profileImage = data.user.profileImage.medium
        )
    }
}