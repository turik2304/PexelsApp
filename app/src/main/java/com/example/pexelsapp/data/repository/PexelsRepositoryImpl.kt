package com.example.pexelsapp.data.repository

import com.example.pexelsapp.data.network.PexelsApi
import com.example.pexelsapp.data.network.responses.PhotosResponse
import com.example.pexelsapp.domain.model.Photo

class PexelsRepositoryImpl(
    override val api: PexelsApi
) : PexelsRepository {

    override suspend fun getPhotos(query: String, perPage: Int): List<Photo> {
        return api.getPhotos(query, perPage).toDomain()
    }

    private fun PhotosResponse.toDomain(): List<Photo> {
        return this.photos.map { photoRemote ->
            Photo(
                id = photoRemote.id,
                url = photoRemote.source.url,
                photographer = photoRemote.photographer
            )
        }
    }
}