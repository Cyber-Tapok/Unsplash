package com.tapok.core

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import javax.inject.Inject

class BlurHashMapper @Inject constructor(
    private val resourceProvider: ResourceProvider,
) {
    var resizeMultiplier = 10
        private set

    fun setResizeMultiplier(multiplier: Int) = apply { resizeMultiplier = multiplier }

    fun transform(blurHash: String, height: Int, wight: Int): Drawable = resourceProvider.getDrawable(
        BlurHashDecoder.decode(
            blurHash = blurHash,
            height = height / resizeMultiplier,
            width = wight / resizeMultiplier
        )!!
    )
}