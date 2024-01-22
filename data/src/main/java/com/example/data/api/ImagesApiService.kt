package com.example.data.api

import com.example.data.api.model.ImagesResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface ImagesApiService {

    @GET("services/feeds/photos_public.gne?format=json&tags=cat&nojsoncallback=1")
    suspend fun getImages(): Response<ImagesResponse>
}
