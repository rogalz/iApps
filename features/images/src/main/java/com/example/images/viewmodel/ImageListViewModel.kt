package com.example.images.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repo.ImageRepository
import com.example.images.ImageListContract
import com.example.images.mapper.ImageListMapper
import com.example.images.model.ImageListViewState
import com.example.images.reducer.ImageListReducer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.net.UnknownHostException
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
        getData()
    }

    fun getData() {
        _viewState.value = reducer.reduceLoading()
        val coroutineException = CoroutineExceptionHandler { _, throwable ->
            _viewState.value = reducer.reduceError(throwable.message ?: "fetching error")
        }

        viewModelScope.launch(coroutineException) {
            try {
                repository.getImages()
                    .collectLatest { entities ->
                        val list = mapper.from(entities)
                        if (list.isNotEmpty())
                            _viewState.value = reducer.reduceImageList(list)
                        else {
                            delay(TIMEOUT)
                            this.cancel()
                        }
                    }
            } catch (e: CancellationException) {
                _viewState.value = reducer.reduceError("timeout")
            }
        }
    }

    companion object {
        const val TIMEOUT = 5000L
    }
}
