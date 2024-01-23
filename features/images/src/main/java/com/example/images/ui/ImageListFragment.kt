package com.example.images.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.images.ImageListContract
import com.example.images.adapter.ImageListAdapter
import com.example.images.databinding.FragmentImagesListBinding
import com.example.images.model.ImageListViewState
import com.example.images.navigator.ImageListNavigatorImpl
import com.example.images.viewmodel.ImageListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {


    private var _binding: FragmentImagesListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ImageListViewModel by viewModels()

    private val navigator: ImageListContract.Navigator by lazy {
        ImageListNavigatorImpl(context = requireContext())
    }
    private val adapter = ImageListAdapter { url ->
        navigator.openBrowser(url)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentImagesListBinding.inflate(inflater, container, false)
        binding.imagesList.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateView()
    }

    private fun updateView() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { uiState ->
                    when (uiState) {
                        is ImageListViewState.Error -> onError(uiState)
                        ImageListViewState.Loading -> onLoading()
                        is ImageListViewState.Success -> onSuccess(uiState)
                    }
                }
            }
        }
    }

    private fun onError(viewState: ImageListViewState.Error) {
        with(binding) {
            loadingView.visibility = GONE
            errorMessage.text = viewState.message
            errorMessage.visibility = VISIBLE
        }
    }

    private fun onLoading() {
        with(binding) {
            loadingView.visibility = VISIBLE
            errorMessage.visibility = GONE
        }
    }

    private fun onSuccess(viewState: ImageListViewState.Success) {
        with(binding) {
            loadingView.visibility = GONE
            errorMessage.visibility = GONE
        }
        adapter.submitList(viewState.images)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


