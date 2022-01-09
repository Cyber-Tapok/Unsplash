package com.tapok.unsplash

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.tapok.core.Screens

object ScreensImpl : Screens {
    override fun main() = FragmentScreen { MainScreenFragment() }

    override fun collections() = TODO()

    override fun photoDetail() = TODO()
}