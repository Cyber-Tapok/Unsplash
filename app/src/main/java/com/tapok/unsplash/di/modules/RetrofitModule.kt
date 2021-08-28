package com.tapok.unsplash.di.modules

import com.google.gson.GsonBuilder
import com.tapok.unsplash.model.api.RetrofitClient
import com.tapok.unsplash.model.api.UnsplashService
import com.tapok.unsplash.model.api.interceptor.AuthAppInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    @Provides
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthAppInterceptor())
            .build()
    }

    @Provides
    fun provideConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit {
        return RetrofitClient.getRetrofit(gsonConverterFactory, okHttpClient)
    }

    @Provides
    fun provideUnsplashService(retrofit: Retrofit): UnsplashService {
        return retrofit.create(UnsplashService::class.java)
    }
}