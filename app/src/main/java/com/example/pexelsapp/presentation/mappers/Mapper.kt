package com.example.pexelsapp.presentation.mappers

import com.example.pexelsapp.domain.model.Photo
import com.example.pexelsapp.presentation.model.PhotoUI

fun Photo.toPresentation(): PhotoUI = PhotoUI(
    id = id,
    url = url,
    photographer = photographer
)