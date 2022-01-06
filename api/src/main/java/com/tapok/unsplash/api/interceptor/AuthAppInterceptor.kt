package com.tapok.unsplash.api.interceptor

import com.tapok.unsplash.api.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthAppInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Accept-Version", "v1")
            .addHeader("Authorization", "Client-ID ${BuildConfig.ACCESS_KEY}")
            .build()
        return chain.proceed(request)
    }
}