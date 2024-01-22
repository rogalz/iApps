package com.example.images.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.images.ImageListContract
import com.example.images.model.ImageListViewState
import com.example.images.navigator.ImageListNavigatorImpl
import com.example.images.viewmodel.ImageListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: ImageListViewModel by viewModels()

    private val navigator: ImageListContract.Navigator by lazy {
        ImageListNavigatorImpl(context = requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return null
    }


    private fun updateView() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect { uiState ->
                    when (uiState) {
                        ImageListViewState.Error -> onError()
                        ImageListViewState.Loading -> onLoading()
                        is ImageListViewState.Success -> onSuccess(uiState)
                    }
                }
            }
        }
    }

    private fun onError() {

    }

    private fun onLoading() {

    }

    private fun onSuccess(viewState: ImageListViewState.Success) {

    }
}


