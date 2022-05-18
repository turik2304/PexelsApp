package com.example.pexelsapp.data.repository

import com.example.pexelsapp.data.network.PexelsApi
import com.example.pexelsapp.domain.model.Photo

interface PexelsRepository {
    val api: PexelsApi
    suspend fun getPhotos(query: String, perPage: Int): List<Photo>
}