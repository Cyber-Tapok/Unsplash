package com.tapok.unsplash

import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.tapok.core.Screens
import com.tapok.unsplash.detail_photo_screen.PhotoDetailFragment

object ScreensImpl : Screens {
    override fun main() = FragmentScreen { MainScreenFragment() }

    override fun collections() = TODO()

    override fun photoDetail(photoId: String) = FragmentScreen { PhotoDetailFragment.newInstance(photoId) }
}