package com.tapok.core.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.tapok.core.ui.databinding.PhotoStatisticLayoutBinding
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PhotoStatistic @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private val binding = PhotoStatisticLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    var likes: Int by binding.likes.statInt()
    var downloads: Int by binding.downloads.statInt()
    var views: Int by binding.views.statInt()

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

internal fun TextView.statInt(): ReadWriteProperty<Any, Int> =
    object : ReadWriteProperty<Any, Int> {
        private var realValue: Int = 0
        private val multiplier = 1000

        override fun getValue(
            thisRef: Any,
            property: KProperty<*>
        ): Int = realValue

        override fun setValue(
            thisRef: Any,
            property: KProperty<*>, value: Int
        ) {
            realValue = value
            val shortValue = if (realValue >= multiplier) {
                "${realValue/multiplier}K"
            } else realValue.toString()
            text = shortValue
        }

    }