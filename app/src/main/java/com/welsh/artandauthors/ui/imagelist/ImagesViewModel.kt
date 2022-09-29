package com.welsh.artandauthors.ui.imagelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welsh.artandauthors.common.Response
import com.welsh.artandauthors.data.domain.Image
import com.welsh.artandauthors.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    val imageRepository: ImageRepository
): ViewModel(){

    // Backing property to avoid state updates from other classes
    private var _error = MutableStateFlow<String>("")
    // The UI collects from this StateFlow to get its state updates
    val error: StateFlow<String> = _error

    // Backing property to avoid state updates from other classes
    private var _loading = MutableStateFlow<Boolean>(false)

    // The UI collects from this StateFlow to get its state updates
    val loading: StateFlow<Boolean> = _loading

    // Backing property to avoid state updates from other classes
    private var _uiStateImages = MutableStateFlow<List<Image>>(emptyList())
    // The UI collects from this StateFlow to get its state updates
    val uiStateImages: StateFlow<List<Image>> = _uiStateImages

    init {
        getAllImages()
    }

    fun getAllImages(){

        viewModelScope.launch(Dispatchers.IO) {
            imageRepository.getAllImages().collect {
                when (it) {
                    is Response.Success -> {
                        _uiStateImages.value = it.data ?: listOf()
                        _loading.value = false
                    }
                    is Response.Loading -> _loading.value = true
                    is Response.Error -> _error.value = it.message ?: ""
                }
            }
        }
    }
} // End ImagesViewModel