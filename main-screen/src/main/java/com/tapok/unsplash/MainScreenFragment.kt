package com.tapok.unsplash

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tapok.unsplash.databinding.MainScreenBinding
import com.tapok.unsplash.model.MainScreenComponentHolder
import dagger.Lazy
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenFragment : Fragment(R.layout.main_screen) {
    private val binding by viewBinding(MainScreenBinding::bind)

    @Inject
    internal lateinit var viewModelFactory: Lazy<MainScreenViewModel.Factory>

    private val componentHolder: MainScreenComponentHolder by viewModels()
    private val viewModel: MainScreenViewModel by viewModels { viewModelFactory.get() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentHolder.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { data ->
                    data ?: return@collect
                    Toast.makeText(requireContext(), data.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}