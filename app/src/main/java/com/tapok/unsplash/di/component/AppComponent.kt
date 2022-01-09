package com.tapok.unsplash.di.component

import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Router
import com.tapok.core.Screens
import com.tapok.unsplash.MainActivity
import com.tapok.unsplash.api.UnsplashService
import com.tapok.unsplash.di.MainScreenDependency
import com.tapok.unsplash.di.module.MainModule
import com.tapok.unsplash.di.module.NavigationModule
import com.tapok.unsplash.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@[App Component(modules = [MainModule::class, NetworkModule::class, NavigationModule::class])]
interface AppComponent : MainScreenDependency {

    override val unsplashService: UnsplashService
    override val globalRouter: Router
    override val routes: Screens
    override val context: Context

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}


@Scope
annotation class App