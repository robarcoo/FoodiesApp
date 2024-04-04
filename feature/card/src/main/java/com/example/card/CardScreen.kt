package com.example.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.data.dto.Product

@Composable
fun cardBottomBar(product: Product, products: ProductState) {
    BottomAppBar(containerColor = Color.White, modifier = Modifier.navigationBarsPadding()) {
        Column(modifier = Modifier
            .height(150.dp)
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .background(Color.White)) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFF15412)),
                shape = RoundedCornerShape(8.dp),
                onClick = { products.shoppingCart.add(product.id)
                }) {
                Text(
                    text = "В корзину за ${product.priceCurrent} ₽", fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun CardScreen(navController: NavController, products: ProductState, id: Int) {
     val product = products.products.find { product -> product.id == id }
     if (product != null) {
     val tags : List<String> = products.tags.filter { tag -> product.tagIds.contains(tag.id) }.map {it.name}
        Scaffold(bottomBar = { cardBottomBar(product, products) }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(Color.White)
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                    navController.navigate("Catalogue")
                }) {
                    Icon(painterResource(R.drawable.arrowleft), contentDescription = null)
                }
                Image(
                    painterResource(R.drawable.food),
                    contentDescription = "",
                    modifier = Modifier.fillMaxWidth()
                )
                Text(product.name, fontSize = 34.sp, fontWeight = FontWeight.Bold)
                Text(product.description, color = Color(0xFF666666))
                CardInfoLine("Вес", "${product.measure} ${product.measureUnit}")
                CardInfoLine("Энерг. ценность", product.energyPer100Grams.toString())
                CardInfoLine("Белки", product.proteinsPer100Grams.toString())
                CardInfoLine("Жиры", product.fatsPer100Grams.toString())
                CardInfoLine("Углеволы", product.carbohydratesPer100Grams.toString())
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text("Теги: ")
                    tags.forEach { tagName ->
                        Text(text = "$tagName ")
                    }
                }

            }
        }
    } else {
        Column(modifier = Modifier.fillMaxSize().background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Упс, вышла ошибка :(")
        }
     }
}

@Composable
fun CardInfoLine(category : String, value : String) {
    HorizontalDivider()
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = category, color = Color(0xFF666666))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = value)
    }
}