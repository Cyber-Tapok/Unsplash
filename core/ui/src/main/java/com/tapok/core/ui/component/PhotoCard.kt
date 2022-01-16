package com.tapok.core.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ImageView.ScaleType
import com.google.android.material.imageview.ShapeableImageView
import com.tapok.core.ui.R
import com.tapok.core.ui.databinding.PhotoCardLayoutBinding

class PhotoCard @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private val binding = PhotoCardLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    val image: ShapeableImageView get() = binding.photo

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val styleAttrs = context.obtainStyledAttributes(attrs, R.styleable.PhotoCard)
        try {
            with(binding.photo) {
                adjustViewBounds  = styleAttrs.getBoolean(R.styleable.PhotoCard_adjustViewBounds, false)
                scaleType = ScaleType.values()[styleAttrs.getInt(R.styleable.PhotoCard_scaleType, ScaleType.CENTER_CROP.ordinal)]
            }
//            initButton(styleAttrs)

        } finally {
            styleAttrs.recycle()
        }
    }

//    private fun initButton(styleAttrs: TypedArray) = with(binding.button) {
//        isAllCaps = styleAttrs.getBoolean(R.styleable.InfoCard_buttonTextAllCaps, false)
//        val color = styleAttrs.getColor(R.styleable.InfoCard_buttonTextAllCaps, -1)
//        if (color != -1) {
//            setTextColor(color)
//        }
//        text = styleAttrs.getString(R.styleable.InfoCard_buttonText)
//    }

}