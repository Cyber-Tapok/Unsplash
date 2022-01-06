package com.tapok.unsplash.api

import com.tapok.unsplash.api.interceptor.AuthAppInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.unsplash.com/"

    fun getUnsplashService(): UnsplashService {
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthAppInterceptor())
            .build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UnsplashService::class.java)
    }
}