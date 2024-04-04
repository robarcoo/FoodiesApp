package com.example.shoppingcart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.common.CardBottomBar
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
        if (product != null) {
            allShopping.add(product)
        }
    }
    if (products.shoppingCart.isNotEmpty()) {
        Scaffold(
            topBar = { ShoppingCartTopBar(navController) },
            bottomBar = {
                CardBottomBar("Заказать за ${sum / 100} ₽",
                    Click = { products.shoppingCart.clear() })
            }
        ) {
            LazyColumn(contentPadding = it) {
                items(allShopping) {
                    Row {
                        Image(
                            painterResource(com.example.common.R.drawable.food),
                            contentDescription = null
                        )
                        Column() {
                            Text(it.name)
                            PlusMinusButtons(products, it)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Column {
                            ShowPrices(it.priceCurrent, it.priceOld)
                        }
                    }
                }
            }
        }
    } else {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Пусто, выберите блюда\n" +
                    "в каталоге :)", color = Color.Black)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartTopBar(navController: NavController) {
    TopAppBar(navigationIcon = {
        IconButton(onClick = {
            navController.popBackStack()
            navController.navigate("Catalogue")})
        {
            Icon(painter = painterResource(R.drawable.arrowleft), contentDescription = null)
        }
    }, title =  { Text("Корзина", fontWeight = FontWeight.Bold) } )
}
