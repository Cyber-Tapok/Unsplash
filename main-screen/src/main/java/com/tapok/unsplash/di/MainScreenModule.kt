package com.tapok.unsplash.di

import com.tapok.core.Feature
import com.tapok.unsplash.api.UnsplashService
import com.tapok.unsplash.mapper.CollectionMapper
import com.tapok.unsplash.mapper.RandomPhotoMapper
import com.tapok.unsplash.model.MainRepository
import com.tapok.unsplash.model.MainRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal interface MainScreenModule {

    @[Feature Binds]
    fun bindRepository(repositoryImpl: MainRepositoryImpl): MainRepository
}