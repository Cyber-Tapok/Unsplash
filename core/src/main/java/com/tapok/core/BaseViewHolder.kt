package com.tapok.core

import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var item: T? = null
        private set


    open fun setOnClickListener(callback: ((T) -> Unit)?) {
        itemView.setOnClickListener { item?.let { callback?.invoke(it) } }
    }

    @CallSuper
    open fun bind(data: T) {
        item = data
    }
}