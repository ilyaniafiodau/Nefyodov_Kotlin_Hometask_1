package com.example.myapplication.ui.theme

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {
    private val _images = MutableStateFlow<List<ImageResponse>>(emptyList())
    val images: StateFlow<List<ImageResponse>> = _images

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _hasError = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError

    init {
        loadImages()
    }

    fun loadImages(limit: Int = 50) {
        viewModelScope.launch {
            _isLoading.value = true
            _hasError.value = false
            try {
                val images = getImagesAsList(RetrofitClient.instance)
                _images.value = images
            } catch (e: Exception) {
                _images.value = emptyList()
                _hasError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }
}
