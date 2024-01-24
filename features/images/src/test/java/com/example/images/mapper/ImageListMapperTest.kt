package com.example.images.mapper

import com.example.data.database.model.ImageEntity
import com.example.images.model.ImageItem
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import org.junit.Test

class ImageListMapperTest {

    private val timerFormatter: TimeFormatter = mockk(relaxed = true) {
        every { from("date 1") } returns "2020.11.20"
        every { from("date 2") } returns "2020.10.20"
    }

    private val tested = ImageListMapper(timerFormatter)

    @Test
    fun from() {
        val entities = listOf(mockk<ImageEntity>(relaxed = true) {
            every { description } returns "title=\"description 1\">"
            every { url } returns "url 1"
            every { previewURL } returns "previewURL 1"
            every { date } returns "date 1"
        }, mockk<ImageEntity>(relaxed = true) {
            every { description } returns "title=\"description 2\">"
            every { url } returns "url 2"
            every { previewURL } returns "previewURL 2"
            every { date } returns "date 2"
        })

        val result = tested.from(entities)

        val expected = listOf(mockk<ImageItem>(relaxed = true) {
            every { description } returns "description 1"
            every { url } returns "url 1"
            every { previewURL } returns "previewURL 1"
            every { date } returns "2020.11.20"
        }, mockk<ImageItem>(relaxed = true) {
            every { description } returns "description 2"
            every { url } returns "url 2"
            every { previewURL } returns "previewURL 2"
            every { date } returns "2020.10.20"
        })
        assertThat(result.first().date).isEqualTo(expected.first().date)
        assertThat(result.first().description).isEqualTo(expected.first().description)
        assertThat(result.first().previewURL).isEqualTo(expected.first().previewURL)
        assertThat(result.first().url).isEqualTo(expected.first().url)
        assertThat(result.last().date).isEqualTo(expected.last().date)
        assertThat(result.last().description).isEqualTo(expected.last().description)
        assertThat(result.last().previewURL).isEqualTo(expected.last().previewURL)
        assertThat(result.last().url).isEqualTo(expected.last().url)
    }
}