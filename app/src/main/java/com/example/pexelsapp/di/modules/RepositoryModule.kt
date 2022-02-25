package com.example.pexelsapp.di.modules

import com.example.pexelsapp.data.network.PexelsApi
import com.example.pexelsapp.data.repository.PexelsRepository
import com.example.pexelsapp.data.repository.PexelsRepositoryImpl
import com.example.pexelsapp.di.scopes.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class RepositoryModule {

    @Provides
    @AppScope
    fun provideRepository(
        api: PexelsApi,
    ): PexelsRepository =
        PexelsRepositoryImpl(api)

    @Provides
    @AppScope
    fun provideApi(retrofitClient: Retrofit): PexelsApi =
        retrofitClient.create(PexelsApi::class.java)
}
