package com.example.pexelsapp.data.repository

import com.example.pexelsapp.data.network.PexelsApi
import com.example.pexelsapp.data.network.responses.ImageResponse

interface PexelsRepository {
    val api: PexelsApi
    suspend fun getImages(query: String, perPage: Int): ImageResponse
}