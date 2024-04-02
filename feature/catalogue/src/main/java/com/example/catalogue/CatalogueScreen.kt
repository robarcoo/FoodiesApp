package com.example.catalogue


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavController
import com.example.common.swap
import com.example.data.repository.Product
import com.example.data.repository.ProductsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



@Composable
fun CatalogueScreen(navController : NavController) {
    val repository = ProductsRepositoryImpl()
    val products = remember { mutableStateListOf<Product>() }
    LaunchedEffect(key1 = true) {
        products.swap(withContext(Dispatchers.IO) {
            repository.getProducts()
        })
    }
    Column {
        LazyColumn() {
            items(products) {
                product ->
                Box() {
                    if (product.priceOld != null) {
                        Image(
                            contentScale = ContentScale.None,
                            painter = painterResource(id = R.drawable.sale_foreground),
                            contentDescription = "sale"
                        )
                    }
                    if (product.tagIds.contains(2)) {
                        Image(
                            painter = painterResource(id = R.drawable.vegetarian_foreground),
                            contentDescription = "vegetarian"
                        )
                    }
                    if (product.tagIds.contains(4)) {
                        Image(
                            painter = painterResource(id = R.drawable.spicy_foreground),
                            contentDescription = "spicy"
                        )
                    }

                    CatalogueCard(product = product)
                }

            }
        }
    }
}

@Composable
fun CatalogueCard(product: Product) {
    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))) {
        Image(painterResource(id = R.drawable.food), contentDescription = "")
        Text(product.name)
        Text("${product.measure} ${product.measureUnit}")
        Button(onClick = {},
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)) {
            if (product.priceOld == null) {
                Text("${product.priceCurrent}")
            } else {
                Text(text = "${product.priceCurrent} ", fontWeight = FontWeight.Bold)
                Text(text = "${product.priceOld}", style =
                TextStyle(color = Color(0xFF666666), textDecoration = TextDecoration.LineThrough))
            }
        }
    }
}