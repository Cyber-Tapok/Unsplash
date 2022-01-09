package com.tapok.core

import android.content.Context
import android.content.res.Resources
import javax.inject.Inject

class ResourceProvider@Inject constructor(private val context: Context) {
    val resources: Resources get() = context.resources
}