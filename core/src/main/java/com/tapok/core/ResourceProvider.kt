package com.tapok.core

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import javax.inject.Inject

class ResourceProvider@Inject constructor(private val context: Context) {

    fun getDrawable(bitmap: Bitmap) = BitmapDrawable(context.resources, bitmap)
}