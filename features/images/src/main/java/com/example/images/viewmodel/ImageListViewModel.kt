package com.example.images.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repo.ImageRepository
import com.example.images.ImageListContract
import com.example.images.mapper.ImageListMapper
import com.example.images.model.ImageListViewState
import com.example.images.reducer.ImageListReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.timeout
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

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
                    getImages()
                }
                .onFailure {
                    _viewState.value = reducer.reduceError()
                }
        }
    }

    private fun getImages() {
        viewModelScope.launch {

            repository.getImages()
                .timeout(3000.milliseconds)
                .catch { exception ->
                    Log.d("****", "timeout")
                    if (exception is TimeoutCancellationException) {
                        // Catch the TimeoutCancellationException emitted above.
                        // Emit desired item on timeout.

                    } else {
                        // Throw other exceptions.
                        throw exception
                    }
                }.onEach { db ->
                    val list = db.map { entity -> mapper.from(entity) }.sortedByDescending { it.date }
                    _viewState.value = reducer.reduceImageList(list)
                }.stateIn(viewModelScope)
        }
    }
}
