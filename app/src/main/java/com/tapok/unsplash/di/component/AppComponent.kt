package com.tapok.unsplash.di.component

import com.tapok.unsplash.di.module.NetworkModule
import dagger.Component

@Component(modules = [NetworkModule::class])
interface AppComponent {
}