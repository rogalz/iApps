package com.example.images.model

sealed class ImageListViewState {
    data object Loading : ImageListViewState()
    data object Error : ImageListViewState()
    data class Success(val images: List<ImageItem>) : ImageListViewState()
}

