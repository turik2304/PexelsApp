package com.example.pexelsapp.presentation.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.GetPhotosUsecase
import com.example.pexelsapp.domain.model.Photo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
class PhotoViewModel @Inject constructor(
    private val getPhotosUsecase: GetPhotosUsecase
) : ViewModel() {

    private val _photosFlow: MutableSharedFlow<List<Photo>> = MutableSharedFlow()
    val photosFlow: SharedFlow<List<Photo>> = _photosFlow.asSharedFlow()

    private val queryFlow: MutableSharedFlow<SearchQuery> = MutableSharedFlow()

    init {
        viewModelScope.launch {
            queryFlow
                .debounce(700)
                .distinctUntilChanged()
                .mapLatest { (query, perPage) ->
                    getPhotosUsecase.execute(query, perPage)
                }
                .collect { photos ->
                    _photosFlow.emit(photos)
                }
        }
    }

    fun loadPhotos(query: String, perPage: Int = 1) {
        viewModelScope.launch {
            queryFlow.emit(SearchQuery(query, perPage))
        }
    }

    fun onPhotoClick(photo: Photo) {
        Log.d("xxx", "photoClick $photo")
    }

    private data class SearchQuery(
        val query: String,
        val perPage: Int
    )
}

