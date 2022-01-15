package com.tapok.unsplash.detail_photo_screen.di

import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Router
import com.tapok.core.Feature
import com.tapok.core.Screens
import com.tapok.unsplash.api.UnsplashService
import com.tapok.unsplash.detail_photo_screen.PhotoDetailFragment
import dagger.Component

@[Feature Component(dependencies = [PhotoDetailDependency::class], modules = [PhotoDetailModule::class])]
internal interface PhotoDetailComponent {

    fun inject(fragment: PhotoDetailFragment)

    @Component.Builder
    interface Builder {

        fun deps(photoDetailDependency:PhotoDetailDependency): Builder

        fun build(): PhotoDetailComponent
    }
}

interface PhotoDetailDependency {

    val unsplashService: UnsplashService
    val globalRouter: Router
    val routes: Screens
    val context: Context
}

interface PhotoDetailDependencyProvider {
    val photoDetailDependency: PhotoDetailDependency
}

val Context.photoDetailDependencyProvider: PhotoDetailDependencyProvider
    get() = when (this) {
        is PhotoDetailDependencyProvider -> this
        is Application -> error("need impl PhotoDetailDependency")
        else -> applicationContext.photoDetailDependencyProvider
    }