package com.example.common

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.Product
import com.example.data.repository.ProductsRepository
import com.example.data.repository.ProductsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository : ProductsRepositoryImpl) : ViewModel() {
    val products = MutableStateFlow(listOf<Product>())
    init {
        viewModelScope.launch(Dispatchers.IO) {
            products.value = repository.getProducts()
        }
    }

}