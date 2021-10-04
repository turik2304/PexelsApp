package com.example.pexelsapp.data.network.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageResponse(
    @SerialName("photos")
    val photos: List<Photo>?
) {
    val isSuccess = photos != null
}

@Serializable
data class Photo(
    @SerialName("src")
    val source: Source
)

@Serializable
data class Source(
    @SerialName("original")
    val link: String
)