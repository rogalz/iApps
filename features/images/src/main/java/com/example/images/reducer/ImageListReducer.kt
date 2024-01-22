package com.example.images.reducer

import com.example.data.database.model.ImageEntity
import com.example.images.model.ImageListState
import javax.inject.Inject

class ImageListReducer @Inject constructor() {

    fun reduceImageList(state: ImageListState, list: List<ImageEntity>): ImageListState {
        return state.copy(loading = false, error = false, list = list)
    }

    fun reduceLoading(state: ImageListState, loading: Boolean): ImageListState {
        return state.copy(loading = loading, error = false)
    }

    fun reduceError(state: ImageListState, error: Boolean): ImageListState {
        return state.copy(loading = false, error = error)
    }

}