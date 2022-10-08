package com.example.domain.repository

import com.example.data.network.PexelsApi
import com.example.domain.model.Photo

interface PexelsRepository {
    val api: PexelsApi
    suspend fun getPhotos(query: String, perPage: Int): List<Photo>
}