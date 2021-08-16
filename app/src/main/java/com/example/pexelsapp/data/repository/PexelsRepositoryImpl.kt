package com.example.pexelsapp.data.repository

import com.example.pexelsapp.data.network.PexelsApi
import com.example.pexelsapp.data.network.responses.ImageResponse

class PexelsRepositoryImpl(
    override val api: PexelsApi
) : PexelsRepository {

    override suspend fun getImages(query: String, perPage: Int): ImageResponse {
        return api.getImage("sea", 1)
    }
}