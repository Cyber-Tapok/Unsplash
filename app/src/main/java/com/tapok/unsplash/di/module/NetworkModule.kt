package com.tapok.unsplash.di.module

import com.tapok.unsplash.api.RetrofitClient
import com.tapok.unsplash.api.UnsplashService
import com.tapok.unsplash.di.component.App
import dagger.Module
import dagger.Provides

@Module
internal class NetworkModule {

    @Provides
    @App
    fun provideUnsplashService(): UnsplashService {
        return RetrofitClient.getUnsplashService()
    }
}