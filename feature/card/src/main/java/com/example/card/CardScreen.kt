package com.example.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.ProductState
import androidx.navigation.NavController
import com.example.common.BottomBar


@Composable
fun CardScreen(navController: NavController, products: ProductState, id: Int) {
     val product = products.products.find { product -> product.id == id }
     if (product != null) {
     val tags : List<String> = products.tags.filter { tag -> product.tagIds.contains(tag.id) }.map {it.name}

        Scaffold(bottomBar = {
            BottomBar(text = "В корзину за ${product.priceCurrent / 100} ₽") {
            products.shoppingCart.add(product.id)
            }
        }) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(it),
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                    navController.navigate("Catalogue")

                }) {
                    Icon(painterResource(R.drawable.arrowleft), contentDescription = null, tint = Color.Black)
                }
                Image(
                    painterResource(R.drawable.food),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth()
                )
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(product.name, fontSize = 34.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                    Text(product.description, color = Color(0xFF666666),
                        modifier = Modifier.padding(top = 8.dp, bottom = 24.dp))
                    CardInfoLine("Вес", "${product.measure} ${product.measureUnit}")
                    CardInfoLine("Энерг. ценность", product.energyPer100Grams.toString())
                    CardInfoLine("Белки", product.proteinsPer100Grams.toString())
                    CardInfoLine("Жиры", product.fatsPer100Grams.toString())
                    CardInfoLine("Углеволы", product.carbohydratesPer100Grams.toString())
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text("Теги: ", color = Color.Black)
                        tags.forEach { tagName ->
                            Text(text = "$tagName ", color = Color.Black)
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
            Text("Упс, вышла ошибка :(", color = Color.Black)
        }
     }
}

@Composable
fun CardInfoLine(category : String, value : String) {
    HorizontalDivider(color = Color(0xFFC5C5C5))
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 13.dp)) {
        Text(text = category, color = Color(0xFF666666))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = value, color = Color.Black)
    }
}