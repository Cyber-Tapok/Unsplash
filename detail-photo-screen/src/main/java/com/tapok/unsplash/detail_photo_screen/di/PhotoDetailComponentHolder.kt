package com.tapok.unsplash.detail_photo_screen.di

import android.app.Application
import com.tapok.core.ComponentHolder

internal class PhotoDetailComponentHolder(application: Application): ComponentHolder<PhotoDetailComponent>(application) {

    override val component: PhotoDetailComponent by lazy {
        DaggerPhotoDetailComponent.builder().deps(application.photoDetailDependencyProvider.photoDetailDependency).build()
    }
}