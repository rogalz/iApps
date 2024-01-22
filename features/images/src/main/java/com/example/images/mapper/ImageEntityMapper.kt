//package com.example.list.mapper
//
//import com.example.database.model.ImageEntity
//import com.example.list.ui.components.ImageItemViewState
//import javax.inject.Inject
//
//class ImageEntityMapper @Inject constructor() {
//
//    fun from(entity: ImageEntity): ImageItemViewState {
//        return ImageItemViewState(
//            id = entity.id,
//            url = entity.previewURL,
//            userName = entity.user,
//            tags = entity.tags,
//        )
//    }
//}