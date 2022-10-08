package com.example.data.network

import com.example.data.network.model.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsApi {

    @GET("v1/search")
    suspend fun getPhotos(
        @Query("query") query: String,
        @Query("per_page") perPage: Int
    ): PhotosResponse
}