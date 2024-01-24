package com.example.images.viewmodel

import com.example.data.repo.ImageRepository
import com.example.images.mapper.ImageListMapper
import com.example.images.reducer.ImageListViewStateMapper
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)

class ImageListViewModelTest {

    private val reducer: ImageListViewStateMapper = mockk(relaxed = true)
    private val repository: ImageRepository = mockk(relaxed = true)
    private val mapper: ImageListMapper = mockk(relaxed = true)
    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private val tested = ImageListViewModel(
        reducer = reducer,
        repository = repository,
        mapper = mapper,
        dispatcher = dispatcher,
    )

    @Test
    fun getData() {
        tested.getData()

        coVerify { repository.getImages() }
    }

    @Test
    fun showsError() {
        coEvery { repository.getImages() } throws Throwable("error")

        tested.getData()

        verify { reducer.toError("error") }
    }
}