package com.example.pexelsapp.domain

import com.example.pexelsapp.data.network.responses.ImageResponse
import com.example.pexelsapp.data.repository.PexelsRepository
import javax.inject.Inject

class GetImagesUsecase @Inject constructor(
    val repository: PexelsRepository
) {

    suspend fun execute(query: String, perPage: Int = 1): ImageResponse {
        return repository.getImages(query, perPage)
    }
}