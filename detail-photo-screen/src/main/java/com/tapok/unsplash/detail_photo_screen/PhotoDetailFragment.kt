package com.tapok.unsplash.detail_photo_screen

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import argument
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tapok.core.ScreenState
import com.tapok.unsplash.detail_photo_screen.databinding.DetailPhotoFragmentBinding
import com.tapok.unsplash.detail_photo_screen.di.PhotoDetailComponentHolder
import com.tapok.unsplash.detail_photo_screen.model.Photo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PhotoDetailFragment : Fragment(R.layout.detail_photo_fragment) {
    private val binding: DetailPhotoFragmentBinding by viewBinding(DetailPhotoFragmentBinding::bind)
    private var photoId by argument<String>()
    private val componentHolder: PhotoDetailComponentHolder by viewModels()

    @Inject
    internal lateinit var viewModelFactory: PhotoDetailViewModelAssistedFactory

    private val viewModel: PhotoDetailViewModel by viewModels { viewModelFactory.create(photoId) }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentHolder.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state
                    .collect(this@PhotoDetailFragment::stateHandler)
            }
        }
    }

    private fun stateHandler(state: ScreenState<Photo>) = when (state) {
        is ScreenState.OnError -> Toast.makeText(requireContext(), state.e.localizedMessage, Toast.LENGTH_LONG).show()
        ScreenState.OnLoad -> {}
        is ScreenState.OnSuccess -> Toast.makeText(requireContext(), state.data.toString(), Toast.LENGTH_LONG).show()
    }


    companion object {

        @JvmStatic
        fun newInstance(photoId: String): PhotoDetailFragment {
            return PhotoDetailFragment().apply {
                this.photoId = photoId
            }
        }
    }
}