package com.tapok.unsplash.detail_photo_screen.di

import com.tapok.core.Feature
import com.tapok.unsplash.detail_photo_screen.model.PhotoDetailRepository
import com.tapok.unsplash.detail_photo_screen.model.PhotoDetailRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
internal interface PhotoDetailModule {

    @[Feature Binds]
    fun bindRepository(photoDetailRepository: PhotoDetailRepositoryImpl): PhotoDetailRepository
}