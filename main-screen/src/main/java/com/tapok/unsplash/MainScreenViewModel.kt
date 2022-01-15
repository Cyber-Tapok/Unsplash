package com.tapok.unsplash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.tapok.core.ScreenState
import com.tapok.core.Screens
import com.tapok.core.onError
import com.tapok.unsplash.domain.MainInteractor
import com.tapok.unsplash.domain.MainScreenData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

internal class MainScreenViewModel(
    private val interactor: MainInteractor,
    private val router: Router,
    private val screens: Screens,
) : ViewModel() {

    val state: MutableStateFlow<ScreenState<MainScreenData>> = MutableStateFlow(ScreenState.OnLoad)

    init {
        uploadData()
    }

    private fun uploadData() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.uploadData()
                .onError { state.value = ScreenState.OnError(it) }
                .collect { data ->
                    state.value = ScreenState.OnSuccess(data)
                }
        }
    }

    fun refreshData() {
        uploadData()
    }

    fun navigateToDetail()  {
        state.value.let { screenState ->
            if (screenState is ScreenState.OnSuccess) {
                router.navigateTo(screens.photoDetail(screenState.data.randomPhoto.id))
            }
        }
    }

    internal class Factory @Inject constructor(
        private val interactor: Provider<MainInteractor>,
        private val router: Provider<Router>,
        private val screens: Provider<Screens>,
    ) :
        ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == MainScreenViewModel::class.java)
            return MainScreenViewModel(interactor.get(), router.get(), screens.get()) as T
        }

    }
}