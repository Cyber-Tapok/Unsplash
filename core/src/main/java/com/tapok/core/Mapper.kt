package com.tapok.core

interface Mapper<T, K> {

    fun transform(data: T): K
}