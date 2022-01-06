package com.tapok.core.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.tapok.core.R
import com.tapok.core.databinding.PhotoCardLayoutBinding

class PhotoCard @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private val binding = PhotoCardLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val styleAttrs = context.obtainStyledAttributes(attrs, R.styleable.PhotoCard)
        try {
            with(binding) {
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