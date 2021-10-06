package com.example.pexelsapp.di.modules

import androidx.lifecycle.ViewModel
import com.example.pexelsapp.data.repository.PexelsRepository
import com.example.pexelsapp.di.scopes.AppScope
import com.example.pexelsapp.di.scopes.SearchScope
import com.example.pexelsapp.domain.GetImagesUsecase
import com.example.pexelsapp.presentation.PexelsViewModel
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    @SearchScope
    fun provideGetImagesUsecase(repository: PexelsRepository): GetImagesUsecase =
        GetImagesUsecase(repository)

    @Provides
    @SearchScope
    fun provideSearchViewModel(getImagesUsecase: GetImagesUsecase): ViewModel =
        PexelsViewModel(getImagesUsecase)
}
