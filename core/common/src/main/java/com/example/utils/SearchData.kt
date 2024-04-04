package com.example.utils

import com.example.common.ProductState
import java.util.Locale

fun SearchData(query : String, products: ProductState) =
    products.products.filter { product -> product.name.lowercase(Locale.getDefault()).contains(query.lowercase(
        Locale.ROOT)) }

