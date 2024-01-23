package com.example.data.api

import com.example.data.api.model.ImagesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ImagesApiService {

    @GET("services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    suspend fun getImages(@Query("tags") tag: String="cat"): Response<ImagesResponse>

}
