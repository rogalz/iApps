package com.example.images.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repo.ImageRepository
import com.example.images.ImageListContract
import com.example.images.model.ImageListViewState
import com.example.images.reducer.ImageListReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageListViewModel @Inject constructor(
    private val reducer: ImageListReducer,
    private val repository: ImageRepository,
//    private val mapper: ImageListMapper,
) : ViewModel(), ImageListContract.ViewModel {

    private val _viewState: StateFlow<ImageListViewState> = MutableStateFlow(ImageListViewState.Loading)
    override val viewState: StateFlow<ImageListViewState> = _viewState

    init {
        fetchImages()
    }

    private fun fetchImages() {
        viewModelScope.launch {
            repository.fetchImages()
                .onSuccess {
//                    state.value = reducer.reduceImageList(currentState, list = it)
                }
                .onFailure {
//                    state.value = reducer.reduceError(currentState, error = true)
                }
        }
    }

}
