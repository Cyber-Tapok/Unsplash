package com.tapok.unsplash.domain

import com.tapok.unsplash.model.Collection
import com.tapok.unsplash.model.RandomPhoto

internal data class MainScreenData(
    val randomPhoto: RandomPhoto,
    val collection: List<Collection>,
)
