package com.example.domain.usecase

interface UseCase<in Params, out T> {
    suspend fun execute(params: Params): T
}