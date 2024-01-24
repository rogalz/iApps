package com.example.images.reducer

import com.example.images.model.ImageListViewState
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ImageListViewStateMapperTest {

    private val tested = ImageListViewStateMapper()

    @Test
    fun toSuccess() {
        val result = tested.toSuccess(emptyList())

        assertThat(result).isInstanceOf(ImageListViewState.Success::class.java)
        assertThat(result).isEqualTo(ImageListViewState.Success(emptyList()))
    }

    @Test
    fun toLoading() {
        val result = tested.toLoading()

        assertThat(result).isInstanceOf(ImageListViewState.Loading::class.java)
    }

        @Test
        fun toError() {
            val result = tested.toError("error")

            assertThat(result).isInstanceOf(ImageListViewState.Error::class.java)
            assertThat(result).isEqualTo(ImageListViewState.Error("error"))
        }
    }
