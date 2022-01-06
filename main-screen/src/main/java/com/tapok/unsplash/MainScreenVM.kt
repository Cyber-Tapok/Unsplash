package com.tapok.unsplash

import androidx.lifecycle.ViewModel
import com.tapok.unsplash.domain.MainInteractor

internal class MainScreenVM constructor(
    private val interactor: MainInteractor,
) : ViewModel() {
}