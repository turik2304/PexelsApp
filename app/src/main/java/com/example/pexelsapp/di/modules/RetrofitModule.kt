package com.example.pexelsapp.di.modules

import com.example.pexelsapp.di.scopes.AppScope
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Named

@Module
class RetrofitModule {

    @Provides
    @AppScope
    fun provideRetrofitClient(
        okHttpClient: OkHttpClient,
        @Named("baseURL") baseURL: String,
        converterFactory: Converter.Factory
    ): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseURL)
            .addConverterFactory(converterFactory)
            .build()

    @Provides
    @AppScope
    @Named("baseURL")
    fun provideBaseURL(): String = "https://api.pexels.com/"

    @Provides
    @AppScope
    fun provideConverterFactory(): Converter.Factory = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }.asConverterFactory("application/json".toMediaType())
}
