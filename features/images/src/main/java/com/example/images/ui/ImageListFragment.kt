package com.example.images.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
                        ImageListViewState.Error -> onError()
                        ImageListViewState.Loading -> onLoading()
                        is ImageListViewState.Success -> onSuccess(uiState)
                    }
                }
            }
        }
    }

    private fun onError() {
        Toast.makeText(requireContext(), "onError", Toast.LENGTH_LONG).show()
    }

    private fun onLoading() {
        Toast.makeText(requireContext(), "onLoading", Toast.LENGTH_LONG).show()
    }

    private fun onSuccess(viewState: ImageListViewState.Success) {
        Toast.makeText(requireContext(), "onSuccess", Toast.LENGTH_LONG).show()
        adapter.submitList(viewState.images)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


