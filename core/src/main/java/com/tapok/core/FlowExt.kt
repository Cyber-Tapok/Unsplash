package com.tapok.core

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow

fun <T> Flow<T>.onError(onError: suspend (Throwable) -> Unit): Flow<T> {
    return flow {
        try {
            this@onError.collect { emit(it) }
        } catch (e: Exception) {
            onError(e)
        }
    }
}