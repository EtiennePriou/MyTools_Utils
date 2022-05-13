package com.etienne.mytoolslibrary.base_classes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun <V : ViewModel> viewModelFactory(createFn: () -> V): ViewModelProvider.Factory {
    return object : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return createFn() as T
        }
    }
}
