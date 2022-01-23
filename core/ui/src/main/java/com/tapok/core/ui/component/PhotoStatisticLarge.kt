package com.tapok.core.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.tapok.core.ui.databinding.PhotoStatisticcLargeLayoutBinding

class PhotoStatisticLarge @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attrs, defStyleAttr) {

    private val binding = PhotoStatisticcLargeLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    var likes: Int by binding.likes.statisticValue()
    var downloads: Int by binding.downloads.statisticValue()
    var views: Int by binding.views.statisticValue()

    init {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
//        val styleAttrs = context.obtainStyledAttributes(attrs, R.styleable.UserView)
//        try {
//
//        } finally {
//            styleAttrs.recycle()
//        }
    }
}