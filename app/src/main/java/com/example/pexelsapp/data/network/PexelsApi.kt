package com.example.pexelsapp.data.network

import com.example.pexelsapp.data.network.responses.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsApi {

    @GET("v1/search")
    suspend fun getImage(
        @Query("query") query: String,
        @Query("per_page") perPage: Int
    ): ImageResponse
}