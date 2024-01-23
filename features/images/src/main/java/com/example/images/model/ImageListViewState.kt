package com.example.images.model

sealed class ImageListViewState {
    data object Loading : ImageListViewState()
    data class Error(val message: String) : ImageListViewState()
    data class Success(val images: List<ImageItem>) : ImageListViewState()
}

