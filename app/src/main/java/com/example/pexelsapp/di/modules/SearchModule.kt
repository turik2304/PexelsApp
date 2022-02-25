package com.example.pexelsapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.pexelsapp.data.repository.PexelsRepository
import com.example.pexelsapp.di.scopes.SearchScope
import com.example.pexelsapp.domain.GetPhotosUsecase
import com.example.pexelsapp.presentation.view_models.PhotoViewModel
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    @SearchScope
    fun provideGetPhotosUsecase(repository: PexelsRepository): GetPhotosUsecase =
        GetPhotosUsecase(repository)

    @Provides
    @SearchScope
    fun provideSearchViewModel(getPhotosUsecase: GetPhotosUsecase): ViewModel =
        PhotoViewModel(getPhotosUsecase)
}
