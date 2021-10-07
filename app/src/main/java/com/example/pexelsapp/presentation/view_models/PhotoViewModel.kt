package com.example.pexelsapp.presentation.view_models

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.GetPhotosUsecase
import com.example.pexelsapp.domain.model.Photo
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
class PhotoViewModel @Inject constructor(
    private val getPhotosUsecase: GetPhotosUsecase
) : ViewModel() {

    private val _photos: MutableSharedFlow<List<Photo>> = MutableSharedFlow()
    val photos: SharedFlow<List<Photo>> = _photos.asSharedFlow()

    fun loadPhotos(query: String, perPage: Int = 1) {
        viewModelScope.launch {
            val photos = getPhotosUsecase.execute(query, perPage)
            _photos.emit(photos)
        }
    }

    fun onPhotoClick(photo: Photo) {
        Log.d("xxx", "photoClick $photo")
    }
}

