package com.example.pexelsapp.data.mappers

import com.example.pexelsapp.data.network.model.PhotosResponse
import com.example.pexelsapp.domain.model.Photo

fun PhotosResponse.toDomain(): List<Photo> {
    return this.photos.map { photoRemote ->
        Photo(
            id = photoRemote.id,
            url = photoRemote.source.url,
            photographer = photoRemote.photographer
        )
    }
}