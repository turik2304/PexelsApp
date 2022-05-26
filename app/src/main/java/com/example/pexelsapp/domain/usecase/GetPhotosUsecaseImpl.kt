package com.example.pexelsapp.domain.usecase

import com.example.pexelsapp.data.repository.PexelsRepository
import com.example.pexelsapp.presentation.mappers.toPresentation
import com.example.pexelsapp.presentation.model.PhotoUI
import javax.inject.Inject

class GetPhotosUsecaseImpl @Inject constructor(
    val repository: PexelsRepository
) : GetPhotosUsecase {

    override suspend fun execute(query: String, perPage: Int): List<PhotoUI> {
        return repository.getPhotos(query, perPage).map { it.toPresentation() }
    }
}