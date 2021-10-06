package com.example.pexelsapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.data.network.responses.ImageResponse
import com.example.pexelsapp.domain.GetImagesUsecase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PexelsViewModel @Inject constructor(
    private val getImagesUsecase: GetImagesUsecase
) : ViewModel() {

    private val _images: MutableSharedFlow<ImageResponse> = MutableSharedFlow()
    val images: SharedFlow<ImageResponse> = _images.asSharedFlow()

    fun loadImage(query: String, perPage: Int = 1) {
        viewModelScope.launch {
            val imageResponse = getImagesUsecase.execute(query, perPage)
            _images.emit(imageResponse)
        }
    }
}