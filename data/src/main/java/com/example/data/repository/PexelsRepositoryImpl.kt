package com.example.data.repository

import com.example.data.mappers.toDomain
import com.example.data.network.PexelsApi
import com.example.domain.model.Photo
import com.example.domain.repository.PexelsRepository

class PexelsRepositoryImpl(
    override val api: PexelsApi
) : PexelsRepository {

    override suspend fun getPhotos(query: String, perPage: Int): List<Photo> {
        return api.getPhotos(query, perPage).toDomain()
    }

}