package com.example.images.reducer

import com.example.images.model.ImageItem
import com.example.images.model.ImageListViewState
import javax.inject.Inject

class ImageListViewStateMapper @Inject constructor() {

    fun toSuccess(imageEntities: List<ImageItem>): ImageListViewState {
        return ImageListViewState.Success(imageEntities)
    }

    fun toLoading(): ImageListViewState {
        return ImageListViewState.Loading
    }

    fun toError(errorMessage: String): ImageListViewState {
        return ImageListViewState.Error(errorMessage)
    }

}