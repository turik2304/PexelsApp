package com.example.pexelsapp.domain.model

data class Photo(
    override val id: Int,
    val url: String,
    val photographer: String
) : PexelsModel
