package com.tapok.unsplash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.github.terrakok.cicerone.Router
import com.tapok.core.*
import com.tapok.unsplash.domain.MainInteractor
import com.tapok.unsplash.domain.MainScreenData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

internal class MainScreenViewModel(
    private val interactor: MainInteractor,
    private val router: Router,
    private val screens: Screens,
) : StateViewModel<ScreenState<MainScreenData>, MainScreenAction, MainScreenEvent>() {

    override fun createInitialState(): ScreenState<MainScreenData> = ScreenState.OnLoad

    init {
        uploadData()
    }

    private fun uploadData() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.uploadData()
                .onError {
                    setState(ScreenState.OnError(it))
                    setAction { MainScreenAction.ShowToast(it.localizedMessage ?: "") }
                }
                .collect { data ->
                    setState(ScreenState.OnSuccess(data))
                }
        }
    }

    override fun handleEvent(event: MainScreenEvent) = when (event) {
        MainScreenEvent.RefreshData -> refreshData()
        MainScreenEvent.OpenCollections -> { /* TODO add list of collections */ }
        is MainScreenEvent.OpenPhotoDetail -> navigateToDetail(event.photoId)
        MainScreenEvent.OpenSearch -> { /* TODO add search */ }
        is MainScreenEvent.OpenCollectionDetail -> { /* TODO add collection detail */ }
    }

    private fun refreshData() {
        uploadData()
    }

    private fun navigateToDetail(photoId: String) {
        router.navigateTo(screens.photoDetail(photoId))
    }

    internal class Factory @Inject constructor(
        private val interactor: Provider<MainInteractor>,
        private val router: Provider<Router>,
        private val screens: Provider<Screens>,
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == MainScreenViewModel::class.java)
            return MainScreenViewModel(interactor.get(), router.get(), screens.get()) as T
        }

    }
}

sealed class MainScreenAction : Action {
    data class ShowToast(val message: String) : MainScreenAction()
}

sealed class MainScreenEvent : Event {
    object RefreshData : MainScreenEvent()
    data class OpenPhotoDetail(val photoId: String) : MainScreenEvent()
    object OpenCollections : MainScreenEvent()
    object OpenSearch: MainScreenEvent()
    data class OpenCollectionDetail(val collectionId: String): MainScreenEvent()
}