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
import coil.load
import com.tapok.core.ScreenState
import com.tapok.unsplash.databinding.MainScreenBinding
import com.tapok.unsplash.model.Collection
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
                root.setOnRefreshListener { viewModel.refreshData() }
                search.setOnClickListener { searchClicked() }
                randomPhoto.setOnClickListener { viewModel.navigateToDetail() }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state
                    .collect(this@MainScreenFragment::stateHandler)
            }
        }
    }

    private fun initAdapter() {
        collectionsAdapter = CollectionAdapter().apply {
            onItemClicked = this@MainScreenFragment::itemClicked
        }
        val footerAdapter = CollectionFooterAdapter().apply {
            onFooterClicked = this@MainScreenFragment::footerClicked
        }
        concatAdapter = ConcatAdapter(collectionsAdapter, footerAdapter)
        with(binding.collections) {
            adapter = concatAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
        }
    }

    private fun footerClicked() {

    }

    private fun itemClicked(item: Collection) {
        Toast.makeText(requireContext(), item.toString(), Toast.LENGTH_LONG).show()
    }

    private fun searchClicked() {
        Toast.makeText(requireContext(), "item.toString()", Toast.LENGTH_LONG).show()
    }

    private fun stateHandler(state: ScreenState<MainScreenData>) = when (state) {
        is ScreenState.OnError -> {
            disableLoading()
            Toast.makeText(requireContext(), state.e.localizedMessage, Toast.LENGTH_SHORT).show()
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

    private fun disableLoading() = with(binding) {
        root.isRefreshing = false
        loadingLayout.isVisible = false
    }

    private fun showRandomPhoto(data: RandomPhoto) = with(binding) {
        randomPhoto.image.load(data.thumbnail.url) {
            placeholder(data.thumbnail.placeholder)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        concatAdapter = null
    }
}