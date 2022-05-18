package com.example.pexelsapp.domain.usecase

import com.example.pexelsapp.data.repository.PexelsRepository
import com.example.pexelsapp.presentation.mappers.toPresentation
import com.example.pexelsapp.presentation.model.PhotoUI
import javax.inject.Inject

class GetPhotosUsecase @Inject constructor(
    val repository: PexelsRepository
) {

    suspend fun execute(query: String, perPage: Int = 1): List<PhotoUI> {
        return repository.getPhotos(query, perPage).map { it.toPresentation() }
    }
}