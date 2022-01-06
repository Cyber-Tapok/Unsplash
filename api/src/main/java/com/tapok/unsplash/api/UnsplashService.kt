package com.tapok.unsplash.api

import com.tapok.unsplash.api.model.CollectionResponse
import com.tapok.unsplash.api.model.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashService {

    @GET("/photos/random")
    suspend fun randomPhoto(): PhotoResponse

    @GET("/photos/{photoId}")
    suspend fun photo(@Path("photoId") id: String)

    @GET("/collections")
    suspend fun collections(): List<CollectionResponse>

    @GET("/collections/{collectionId}/photos")
    suspend fun collections(@Path("collectionId") collectionId: String): List<PhotoResponse>
}