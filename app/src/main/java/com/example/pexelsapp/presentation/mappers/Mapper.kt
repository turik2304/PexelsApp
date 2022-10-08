package com.example.pexelsapp.presentation.mappers

import com.example.domain.Photo
import com.example.pexelsapp.presentation.model.PhotoUI

fun com.example.domain.Photo.toPresentation(): PhotoUI = PhotoUI(
    id = id,
    url = url,
    photographer = photographer
)