package com.tapok.unsplash.detail_photo_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tapok.core.ScreenState
import com.tapok.core.onError
import com.tapok.unsplash.detail_photo_screen.domain.PhotoDetailInteractor
import com.tapok.unsplash.detail_photo_screen.model.Photo
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Provider

internal class PhotoDetailViewModel(
    private val photoId: String,
    private val interactor: PhotoDetailInteractor
) : ViewModel() {

    val state: MutableStateFlow<ScreenState<Photo>> = MutableStateFlow(ScreenState.OnLoad)

    init {
        getPhoto(photoId)
    }

    private fun getPhoto(photoId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getPhoto(photoId)
                .onError { state.value = ScreenState.OnError(it) }
                .collect { data ->
                    state.value = ScreenState.OnSuccess(data)
                }
        }
    }

    fun refreshData() {
        getPhoto(photoId)
    }

    internal class Factory @AssistedInject constructor(
        @Assisted private val photoId: String,
        private val interactor: Provider<PhotoDetailInteractor>) :
        ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == PhotoDetailViewModel::class.java)
            return PhotoDetailViewModel(photoId, interactor.get()) as T
        }

    }
}


@AssistedFactory
internal interface PhotoDetailViewModelAssistedFactory {
    fun create(photoId: String): PhotoDetailViewModel.Factory
}