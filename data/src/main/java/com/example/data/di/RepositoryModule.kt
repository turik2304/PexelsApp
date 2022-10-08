package com.example.data.di

import com.example.data.network.PexelsApi
import com.example.data.repository.PexelsRepositoryImpl
import com.example.domain.repository.PexelsRepository
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
    fun provideRepository(api: PexelsApi): PexelsRepository = PexelsRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideApi(retrofitClient: Retrofit): PexelsApi =
        retrofitClient.create(PexelsApi::class.java)
}
