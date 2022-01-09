package com.tapok.unsplash.di

import com.tapok.core.Feature
import com.tapok.unsplash.api.UnsplashService
import com.tapok.unsplash.mapper.CollectionMapper
import com.tapok.unsplash.mapper.RandomPhotoMapper
import com.tapok.unsplash.model.MainRepository
import com.tapok.unsplash.model.MainRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
internal class MainScreenModule {

    @Feature
    @Provides
    fun provideRepository(
        service: UnsplashService,
        randomPhotoMapper: RandomPhotoMapper,
        collectionMapper: CollectionMapper,
    ): MainRepository = MainRepositoryImpl(service, randomPhotoMapper, collectionMapper)
}