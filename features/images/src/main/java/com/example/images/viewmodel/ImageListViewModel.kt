package com.example.images.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repo.ImageRepository
import com.example.images.ImageListContract
import com.example.images.mapper.ImageListMapper
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
    private val mapper: ImageListMapper,
) : ViewModel(), ImageListContract.ViewModel {

    private val _viewState: MutableStateFlow<ImageListViewState> = MutableStateFlow(ImageListViewState.Loading)
    override val viewState: StateFlow<ImageListViewState> = _viewState

    init {
        fetchImages()
    }

    private fun fetchImages() {
        viewModelScope.launch {
            _viewState.value = reducer.reduceLoading()

            repository.fetchImages()
                .onSuccess { entities ->
                    val list = entities.map { entity -> mapper.from(entity) }.sortedByDescending { it.date }
                    _viewState.value = reducer.reduceImageList(list)
                }
                .onFailure {
                    _viewState.value = reducer.reduceError()
                }
        }
    }

}
