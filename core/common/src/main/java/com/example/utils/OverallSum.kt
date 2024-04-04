package com.example.utils

import com.example.common.ProductState

fun overallSum(products: ProductState) : Int {
    val allBought = products.shoppingCart.groupingBy { it }.eachCount()
    var sum = 0
    var inKopecks = 0
    allBought.forEach {bought ->
        inKopecks = (products.products.find { it.id == bought.key }?.priceCurrent) ?: 0
        sum += (inKopecks * bought.value / 100)
    }
    return sum
}