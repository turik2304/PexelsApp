package com.example.pexelsapp.data.repository

import com.example.pexelsapp.data.mappers.toDomain
import com.example.pexelsapp.data.network.PexelsApi
import com.example.pexelsapp.domain.model.Photo

class PexelsRepositoryImpl(
    override val api: PexelsApi
) : PexelsRepository {

    override suspend fun getPhotos(query: String, perPage: Int): List<Photo> {
        return api.getPhotos(query, perPage).toDomain()
    }

}