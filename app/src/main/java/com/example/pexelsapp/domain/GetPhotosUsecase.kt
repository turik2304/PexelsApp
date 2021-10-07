package com.example.pexelsapp.domain

import com.example.pexelsapp.data.repository.PexelsRepository
import com.example.pexelsapp.domain.model.Photo
import javax.inject.Inject

class GetPhotosUsecase @Inject constructor(
    val repository: PexelsRepository
) {

    suspend fun execute(query: String, perPage: Int = 1): List<Photo> {
        return repository.getPhotos(query, perPage)
    }
}