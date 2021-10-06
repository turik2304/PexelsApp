package com.example.pexelsapp.domain

import com.example.pexelsapp.data.network.responses.ImageResponse
import com.example.pexelsapp.data.repository.PexelsRepository

class GetImagesUsecase(private val repository: PexelsRepository) {

    suspend fun execute(query: String, perPage: Int = 1): ImageResponse {
        return repository.getImages(query, perPage)
    }
}