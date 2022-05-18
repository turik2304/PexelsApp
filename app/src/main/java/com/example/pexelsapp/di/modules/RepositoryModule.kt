package com.example.pexelsapp.di.modules

import com.example.pexelsapp.data.network.PexelsApi
import com.example.pexelsapp.data.repository.PexelsRepository
import com.example.pexelsapp.data.repository.PexelsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        api: PexelsApi,
    ): PexelsRepository =
        PexelsRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideApi(retrofitClient: Retrofit): PexelsApi =
        retrofitClient.create(PexelsApi::class.java)
}
