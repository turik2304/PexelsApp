package com.example.pexelsapp.data.network

import com.example.pexelsapp.data.network.responses.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsApi {

    @GET("v1/search")
    suspend fun getPhotos(
        @Query("query") query: String,
        @Query("per_page") perPage: Int
    ): PhotosResponse
}