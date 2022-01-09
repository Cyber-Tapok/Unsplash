package com.tapok.unsplash.di.module

import android.app.Application
import android.content.Context
import com.tapok.unsplash.di.component.App
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @App
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}