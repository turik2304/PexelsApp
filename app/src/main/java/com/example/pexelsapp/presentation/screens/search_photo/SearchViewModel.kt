package com.example.pexelsapp.presentation.screens.search_photo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pexelsapp.domain.usecase.GetPhotosUsecase
import com.example.pexelsapp.presentation.model.PhotoUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@FlowPreview
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getPhotosUsecase: GetPhotosUsecase
) : ViewModel() {

    private val _photosFlow: MutableSharedFlow<List<PhotoUI>> = MutableSharedFlow()
    val photosFlow: SharedFlow<List<PhotoUI>> = _photosFlow.asSharedFlow()

    private val queryFlow: MutableSharedFlow<SearchQuery> = MutableSharedFlow()

    init {
        viewModelScope.launch {
            queryFlow
                .filter { it.query.isNotBlank() }
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

    fun onPhotoClick(photo: PhotoUI) {
        Log.d("xxx", "photoClick $photo")
    }

    private data class SearchQuery(
        val query: String,
        val perPage: Int
    )
}

