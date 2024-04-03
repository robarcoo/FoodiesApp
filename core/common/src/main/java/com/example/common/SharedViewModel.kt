package com.example.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.repository.ProductsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(private val repository : ProductsRepositoryImpl) : ViewModel() {
    val productState = MutableStateFlow(ProductState())
    init {
        viewModelScope.launch(Dispatchers.IO) {
            productState.value.products = repository.getProducts()
            productState.value.categories = repository.getCategories()
            productState.value.tags = repository.getTags()
            productState.value.categoriesWithProducts = productState.value.categories.map { category -> productState.value.products.filter { product -> product.categoryId == category.id }}
        }
    }

}