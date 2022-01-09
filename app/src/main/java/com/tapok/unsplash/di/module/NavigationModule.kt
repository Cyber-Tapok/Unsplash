package com.tapok.unsplash.di.module

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.tapok.core.Screens
import com.tapok.unsplash.ScreensImpl
import com.tapok.unsplash.di.component.App
import dagger.Module
import dagger.Provides

@Module
class NavigationModule {

    @Provides
    @App
    fun provideCiceroneRouter(): Cicerone<Router> {
        return Cicerone.create()
    }

    @Provides
    @App
    fun provideRouter(routerCicerone: Cicerone<Router>): Router {
        return routerCicerone.router
    }

    @Provides
    @App
    fun provideNavigationHolder(routerCicerone: Cicerone<Router>): NavigatorHolder {
        return routerCicerone.getNavigatorHolder()
    }

    @Provides
    @App
    fun provideRoutes(): Screens {
        return ScreensImpl
    }
}