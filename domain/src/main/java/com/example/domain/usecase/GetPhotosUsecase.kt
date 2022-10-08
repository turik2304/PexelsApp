package com.example.domain.usecase

import com.example.domain.model.Photo
import com.example.domain.repository.PexelsRepository
import javax.inject.Inject

class GetPhotosUsecase @Inject constructor(
    val repository: PexelsRepository
) : UseCase<GetPhotosUsecase.Params, List<Photo>> {

    override suspend fun execute(params: Params): List<Photo> {
        return repository.getPhotos(params.query, params.perPage)
    }

    class Params(val query: String, val perPage: Int)
}