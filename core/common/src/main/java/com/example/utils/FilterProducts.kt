package com.example.utils

import com.example.common.ProductState
import com.example.data.dto.Product

fun filterProducts(product: ProductState, chunk : List<Product>) : List<Product> {
    var result = chunk
    product.appliedTags.forEach {
        result = when(it) {
            2 -> result.filter { product -> product.tagIds.contains(2) }
            4 -> result.filter { product -> product.tagIds.contains(4) }
            6 -> result.filter { product -> product.priceOld != null }
            else -> result
        }
    }
    return result
}
