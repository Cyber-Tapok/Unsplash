package com.tapok.unsplash.model

import android.app.Application
import com.tapok.core.ComponentHolder
import com.tapok.unsplash.di.DaggerMainScreenComponent
import com.tapok.unsplash.di.MainScreenComponent
import com.tapok.unsplash.di.mainScreenDependencyProvider

internal class MainScreenComponentHolder(application: Application) : ComponentHolder<MainScreenComponent>(application) {

    override val component by lazy {
        DaggerMainScreenComponent.builder()
            .deps(application.mainScreenDependencyProvider.mainScreenDependency)
            .build()
    }
}