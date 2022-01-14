package com.tapok.unsplash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tapok.core.onError
import com.tapok.unsplash.domain.MainInteractor
import com.tapok.unsplash.domain.MainScreenData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

internal class MainScreenViewModel(private val interactor: MainInteractor) : ViewModel() {

    val state: MutableStateFlow<MainScreenState> = MutableStateFlow(MainScreenState.OnLoad)

    init {
        uploadData()
    }

    private fun uploadData() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.uploadData()
                .onError { state.value = MainScreenState.OnError(it) }
                .collect { data ->
                    state.value = MainScreenState.OnSuccess(data)
                }
        }
    }

    fun refreshData() {
        uploadData()
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

internal sealed class MainScreenState {
    object OnLoad : MainScreenState()
    data class OnError(val e: Throwable) : MainScreenState()
    data class OnSuccess(val data: MainScreenData) : MainScreenState()
}
