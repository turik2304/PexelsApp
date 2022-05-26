package com.example.pexelsapp.domain.usecase

import com.example.pexelsapp.presentation.model.PhotoUI

interface GetPhotosUsecase {
    suspend fun execute(query: String, perPage: Int): List<PhotoUI>
}