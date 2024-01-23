package com.example.images.reducer

import com.example.images.model.ImageItem
import com.example.images.model.ImageListViewState
import javax.inject.Inject

class ImageListReducer @Inject constructor() {

    fun reduceImageList(imageEntities: List<ImageItem>): ImageListViewState {
        return ImageListViewState.Success(imageEntities)
    }

    fun reduceLoading(): ImageListViewState {
        return ImageListViewState.Loading
    }

    fun reduceError(): ImageListViewState {
        return ImageListViewState.Error
    }

}