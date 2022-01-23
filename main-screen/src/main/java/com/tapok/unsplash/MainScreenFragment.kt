package com.tapok.unsplash

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.tapok.core.ScreenState
import com.tapok.unsplash.databinding.MainScreenBinding
import com.tapok.unsplash.di.MainScreenComponentHolder
import com.tapok.unsplash.domain.MainScreenData
import com.tapok.unsplash.model.RandomPhoto
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
    private var collectionsAdapter: CollectionAdapter? = null
    private var concatAdapter: ConcatAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentHolder.component.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        view.post {
            with(binding) {
                root.setOnRefreshListener { viewModel.setEvent(MainScreenEvent.RefreshData) }
                search.setOnClickListener { viewModel.setEvent(MainScreenEvent.OpenSearch) }
                randomPhoto.setOnPhotoClicked { viewModel.setEvent(MainScreenEvent.OpenPhotoDetail(it)) }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect(this@MainScreenFragment::handleState)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.action.collect(this@MainScreenFragment::handleAction)
        }
    }

    private fun initAdapter() {
        collectionsAdapter = CollectionAdapter().apply {
            onItemClicked = { viewModel.setEvent(MainScreenEvent.OpenCollections) }
        }
        val footerAdapter = CollectionFooterAdapter().apply {
            onFooterClicked = { viewModel.setEvent(MainScreenEvent.OpenCollections) }
        }
        concatAdapter = ConcatAdapter(collectionsAdapter, footerAdapter)
        with(binding.collections) {
            adapter = concatAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
        }
    }

    private fun handleState(state: ScreenState<MainScreenData>) = when (state) {
        is ScreenState.OnError -> {
            disableLoading()
        }
        ScreenState.OnLoad -> with(binding) {
            dataLayout.isVisible = false
        }
        is ScreenState.OnSuccess -> {
            binding.dataLayout.isVisible = true
            disableLoading()
            showRandomPhoto(state.data.randomPhoto)
            collectionsAdapter?.submitList(state.data.collection)
        }
    }

    private fun handleAction(action: MainScreenAction) = when (action) {
        is MainScreenAction.ShowToast -> Toast.makeText(requireContext(), action.message, Toast.LENGTH_SHORT).show()
    }

    private fun disableLoading() = with(binding) {
        root.isRefreshing = false
        loadingLayout.isVisible = false
    }

    private fun showRandomPhoto(data: RandomPhoto) = with(binding) {
        randomPhoto.placePhoto(data.thumbnail)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        concatAdapter = null
    }
}