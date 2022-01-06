package com.tapok.unsplash.model

import com.tapok.unsplash.api.UnsplashService
import com.tapok.unsplash.mapper.CollectionMapper
import com.tapok.unsplash.mapper.RandomPhotoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal interface MainRepository {

    fun getRandomPhoto(): Flow<RandomPhoto>
    fun getCollections(): Flow<List<Collection>>
}

internal class MainRepositoryImpl @Inject constructor(
    private val service: UnsplashService,
    private val randomPhotoMapper: RandomPhotoMapper,
    private val collectionMapper: CollectionMapper,
) : MainRepository {

    override fun getRandomPhoto() = flow { emit(service.randomPhoto()) }
        .map(randomPhotoMapper::transform)

    override fun getCollections() = flow { emit(service.collections()) }
        .map { list -> list.map(collectionMapper::transform) }
}