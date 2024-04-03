package com.example.utils

import com.example.common.ProductState

fun ApplyTags(products: ProductState, isApplied : Boolean, tag : Int) {
    if (isApplied) {
        if (!products.appliedTags.contains(tag)) {
            products.appliedTags.add(tag)
        }
    } else {
        products.appliedTags.remove(tag)
    }
}