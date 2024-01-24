package com.example.images.mapper

import com.example.data.database.model.ImageEntity
import com.example.images.model.ImageItem
import javax.inject.Inject

class ImageListMapper @Inject constructor(
    private val timerFormatter: TimeFormatter
) {

    fun from(entities: List<ImageEntity>): List<ImageItem> {
        return entities.map { entity -> from(entity) }.sortedByDescending { imageItem -> imageItem.date }
    }

    private fun from(entity: ImageEntity): ImageItem {
        val description = getDescription(entity)
        return ImageItem(
            description = description,
            url = entity.url,
            previewURL = entity.previewURL,
            date = timerFormatter.from(entity.date)
        )
    }

    private fun getDescription(entity: ImageEntity): String {
        val prefix = "title=\""
        val suffix = "\">"
        val regex = "$prefix(.+)$suffix".toRegex()
        val match = regex.find(entity.description)
        val machAsString = match?.value ?: ""
        return machAsString.removePrefix(prefix).removeSuffix(suffix)
    }
}
