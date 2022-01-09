package com.tapok.unsplash.di

import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Router
import com.tapok.core.Feature
import com.tapok.core.Screens
import com.tapok.unsplash.MainScreenFragment
import com.tapok.unsplash.api.UnsplashService
import dagger.BindsInstance
import dagger.Component

@[Feature Component(dependencies = [MainScreenDependency::class], modules = [MainScreenModule::class])]
internal interface MainScreenComponent {

    fun inject(fragment: MainScreenFragment)

    @Component.Builder
    interface Builder {

        fun deps(mainScreenDependency: MainScreenDependency): Builder

        fun build(): MainScreenComponent
    }
}

interface MainScreenDependency {

    val unsplashService: UnsplashService
    val globalRouter: Router
    val routes: Screens
    val context: Context
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

