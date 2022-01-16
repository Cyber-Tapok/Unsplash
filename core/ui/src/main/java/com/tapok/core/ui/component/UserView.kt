package com.tapok.core.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView
import com.tapok.core.ui.R
import com.tapok.core.ui.databinding.UserViewLayoutBinding

class UserView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private val binding = UserViewLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    val picture: ShapeableImageView get() = binding.picture
    val username: TextView get() = binding.username
    val fullName: TextView get() = binding.fullName

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        val styleAttrs = context.obtainStyledAttributes(attrs, R.styleable.UserView)
        try {

        } finally {
            styleAttrs.recycle()
        }
    }
}