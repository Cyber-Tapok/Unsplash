package com.tapok.unsplash.di

import android.app.Application
import android.content.Context
import com.tapok.unsplash.detail_photo_screen.di.PhotoDetailDependency
import com.tapok.unsplash.detail_photo_screen.di.PhotoDetailDependencyProvider
import com.tapok.unsplash.di.component.AppComponent
import com.tapok.unsplash.di.component.DaggerAppComponent

class App : Application(), MainScreenDependencyProvider, PhotoDetailDependencyProvider {

    private val _appComponent by lazy { DaggerAppComponent.builder().application(this).build() }

    val appComponent get() = _appComponent


    override val mainScreenDependency: MainScreenDependency get() = _appComponent
    override val photoDetailDependency: PhotoDetailDependency get() = _appComponent
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }