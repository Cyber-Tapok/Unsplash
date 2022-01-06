package com.tapok.unsplash.domain

import com.tapok.unsplash.model.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip
import javax.inject.Inject

internal class MainInteractor @Inject constructor(private val repository: MainRepository) {

    fun uploadData(): Flow<MainScreenData> = repository.getRandomPhoto()
        .zip(repository.getCollections()) { randomPhoto, collections -> MainScreenData(randomPhoto, collections) }
}

