package com.tapok.unsplash.di.component

import android.app.Application
import android.content.Context
import com.tapok.unsplash.api.UnsplashService
import com.tapok.unsplash.di.MainScreenDependency
import com.tapok.unsplash.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@[App Component(modules = [NetworkModule::class])]
interface AppComponent: MainScreenDependency{

     override val unsplashService: UnsplashService

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}


@Scope
annotation class App