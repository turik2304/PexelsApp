package com.example.pexelsapp.di.modules

import com.example.pexelsapp.data.repository.PexelsRepository
import com.example.pexelsapp.domain.usecase.GetPhotosUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SearchModule {

    @Provides
    fun provideGetPhotosUsecase(repository: PexelsRepository): GetPhotosUsecase =
        GetPhotosUsecase(repository)
}
