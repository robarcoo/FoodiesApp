package com.example.catalogue


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.swap
import com.example.data.repository.Product
import com.example.data.repository.ProductsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext



@Composable
fun CatalogueScreen(navController: NavController, products: List<Product>) {
    val chunks = products.chunked(2)
    Column (modifier = Modifier.fillMaxSize().background(Color.White)) {
        LazyColumn(modifier = Modifier.fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 0.dp, bottom = 0.dp)) {
            items(chunks) {
                Row(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max)) {
                    it.forEach { product ->
                        Box(modifier = Modifier.weight(1f)) {
                            CatalogueCard(product = product)
                            Row(modifier = Modifier.fillMaxWidth().padding(top = 17.dp, start = 12.dp, end = 0.dp, bottom = 0.dp)) {
                                if (product.priceOld != null) {
                                    Image(
                                        painter = painterResource(id = R.drawable.sale_foreground),
                                        contentDescription = "sale",
                                        modifier = Modifier.size(35.dp).padding(top = 0.dp, start = 0.dp, end = 6.dp, bottom = 0.dp)
                                    )
                                }
                                if (product.tagIds.contains(2)) {
                                    Image(
                                        painter = painterResource(id = R.drawable.vegetarian_foreground),
                                        contentDescription = "vegetarian",
                                        modifier = Modifier.size(35.dp).padding(top = 0.dp, start = 0.dp, end = 6.dp, bottom = 0.dp)
                                    )
                                }
                                if (product.tagIds.contains(4)) {
                                    Image(
                                        painter = painterResource(id = R.drawable.spicy_foreground),
                                        contentDescription = "spicy",
                                        modifier = Modifier.size(35.dp).padding(top = 0.dp, start = 0.dp, end = 6.dp, bottom = 0.dp)
                                    )
                                }
                            }

                        }
                    }
                }

            }
        }
    }
}

@Composable
fun CatalogueCard(product: Product) {
    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        modifier = Modifier.padding(4.dp).fillMaxHeight()) {
        Image(painterResource(id = R.drawable.food), modifier = Modifier.fillMaxWidth(),
            contentDescription = "")
        Column(modifier = Modifier.padding(12.dp).weight(1f)) {
            Text(product.name, color = Color.Black)
            Spacer(modifier = Modifier.size(4.dp))
            Text("${product.measure} ${product.measureUnit}", color = Color(0xFF666666))
            Spacer(modifier = Modifier.size(12.dp).weight(1f))
            Button(
                onClick = {},
                elevation = ButtonDefaults.buttonElevation(3.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                if (product.priceOld == null) {
                    Text("${product.priceCurrent / 100} ₽", color = Color.Black)
                } else {
                    Text(
                        text = "${product.priceCurrent / 100} ₽ ",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "${product.priceOld!! / 100} ₽", style =
                        TextStyle(
                            color = Color(0xFF666666),
                            textDecoration = TextDecoration.LineThrough
                        )
                    )
                }
            }
        }
    }
}