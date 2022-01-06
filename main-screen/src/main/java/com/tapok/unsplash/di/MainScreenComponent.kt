package com.tapok.unsplash.di

import android.app.Application
import android.content.Context
import com.tapok.core.Feature
import com.tapok.unsplash.api.UnsplashService
import dagger.BindsInstance
import dagger.Component

@[Feature Component]
internal interface MainScreenComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun dependencies(mainScreenDependency: MainScreenDependency): Builder

        fun build(): MainScreenComponent
    }
}

interface MainScreenDependency {

    val unsplashService: UnsplashService
}

interface MainScreenDependencyProvider {
    val mainScreenDependency: MainScreenDependency
}

val Context.mainScreenDependencyProvider: MainScreenDependencyProvider
    get() = when (this) {
        is MainScreenDependencyProvider -> this
        is Application -> error("need impl MainScreenDependency")
        else -> applicationContext.mainScreenDependencyProvider
    }

