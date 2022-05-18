package com.example.pexelsapp.di.modules

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .addNetworkInterceptor(loggingInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor = Interceptor { chain ->
        val request: Request = chain.request()
        val authenticatedRequest: Request = request.newBuilder()
            .header(
                "Authorization",
                "563492ad6f917000010000013f1cbd81129c48c698f2f5026a34895a"
            ).build()
        chain.proceed(authenticatedRequest)
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
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
    @Singleton
    @Named("baseURL")
    fun provideBaseURL(): String = "https://api.pexels.com/"

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }.asConverterFactory("application/json".toMediaType())

}