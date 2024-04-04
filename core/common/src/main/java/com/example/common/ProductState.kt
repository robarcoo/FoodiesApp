package com.example.common

import androidx.compose.runtime.mutableStateListOf
import com.example.data.dto.Category
import com.example.data.dto.Product
import com.example.data.dto.Tag

data class ProductState(
    var products : List<Product> = mutableStateListOf(),
    var categories : List<Category> = mutableStateListOf(),
    var tags : List<Tag> = mutableStateListOf(),
    var categoriesWithProducts : List<List<Product>> = mutableStateListOf(),
    var appliedTags: MutableList<Int> = mutableStateListOf(),
    var shoppingCart: MutableList<Int> = mutableStateListOf()
)