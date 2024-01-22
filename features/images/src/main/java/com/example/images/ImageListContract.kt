package com.example.images

import com.example.images.model.ImageListViewState
import kotlinx.coroutines.flow.StateFlow

interface ImageListContract {

    interface ViewModel {
        val viewState: StateFlow<ImageListViewState>
    }

    interface Navigator {
        fun openBrowser(url: String)
    }
}