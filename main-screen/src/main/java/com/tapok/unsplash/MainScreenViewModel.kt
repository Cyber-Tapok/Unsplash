package com.tapok.unsplash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tapok.unsplash.domain.MainInteractor
import com.tapok.unsplash.domain.MainScreenData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

internal class MainScreenViewModel(private val interactor: MainInteractor) : ViewModel() {

    val state: MutableStateFlow<MainScreenData?> = MutableStateFlow(null)

    init {
        viewModelScope.launch {
            interactor.uploadData()
                .catch { Log.e("TTT", it.toString()) }
                .collect { data ->
                state.value = data
            }
        }
    }

    internal class Factory @Inject constructor(private val interactor: Provider<MainInteractor>) :
        ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == MainScreenViewModel::class.java)
            return MainScreenViewModel(interactor.get()) as T
        }

    }
}
