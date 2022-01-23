package com.tapok.core

sealed class ScreenState<out T>: State {
    object OnLoad : ScreenState<Nothing>()
    data class OnError(val e: Throwable) : ScreenState<Nothing>()
    data class OnSuccess<T>(val data: T) : ScreenState<T>()
}
