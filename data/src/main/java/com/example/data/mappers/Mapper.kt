package com.example.data.mappers

import com.example.data.network.model.PhotosResponse
import com.example.domain.model.Photo

fun PhotosResponse.toDomain(): List<Photo> {
    return this.photos.map { photoRemote ->
        Photo(
            id = photoRemote.id,
            url = photoRemote.source.url,
            photographer = photoRemote.photographer
        )
    }
}