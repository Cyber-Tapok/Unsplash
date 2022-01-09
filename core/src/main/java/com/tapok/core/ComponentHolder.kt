package com.tapok.core

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class ComponentHolder<T>(application: Application) : AndroidViewModel(application) {

    abstract val component: T
}