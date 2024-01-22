//package com.example.list.mapper
//
//import com.example.images.model.ImageListState
//import com.example.list.ui.ImageListScreenViewState
//import javax.inject.Inject
//
//class ImageListMapper @Inject constructor(
//    private val imageEntityMapper: ImageEntityMapper,
//) {
//
//    fun from(state: ImageListState): ImageListScreenViewState {
//        return when {
//            state.error -> ImageListScreenViewState.Error(search = state.search)
//            state.loading -> ImageListScreenViewState.Loading(search = state.search)
//            else -> mapToSuccess(state)
//        }
//    }
//
//    private fun mapToSuccess(state: ImageListState): ImageListScreenViewState.Success {
//        return ImageListScreenViewState.Success(
//            list = state.list.map { imageEntityMapper.from(it) },
//            search = state.search,
//            isDialogVisible = state.isGoToDetailsDialogVisible,
//        )
//    }
//
//}
