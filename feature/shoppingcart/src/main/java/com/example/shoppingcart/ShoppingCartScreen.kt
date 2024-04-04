package com.example.shoppingcart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.BottomBar
import com.example.common.PlusMinusButtons
import com.example.common.ProductState
import com.example.common.ShowPrices
import com.example.data.dto.Product

@Composable
fun ShoppingCartScreen(products: ProductState, navController: NavController) {
    var sum = 0
    val allShopping = mutableListOf<Product>()
    products.shoppingCart.forEach { id ->
        val product = products.products.find { id == it.id }
        sum += product?.priceCurrent ?: 0
        if (product != null && !allShopping.contains(product)) {
            allShopping.add(product)
        }
    }
        Scaffold(modifier = Modifier.background(Color.White),
            topBar = { ShoppingCartTopBar(navController) },
            bottomBar = { if (products.shoppingCart.size > 0) {
                BottomBar("Заказать за ${sum / 100} ₽")
                {
                    products.shoppingCart.clear()
                }
            }
            }
        ) {
            if (products.shoppingCart.isNotEmpty()) {
                LazyColumn(contentPadding = it, modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)) {
                    items(allShopping) { product ->
                        ShoppingCartItem(product = product, products = products)
                    }
                }
            } else {
                CartIsEmpty(it)
            }
        }
}






