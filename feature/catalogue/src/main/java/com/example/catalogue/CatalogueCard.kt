package com.example.catalogue

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.PlusMinusButtons
import com.example.common.ProductState
import com.example.common.ShowPrices
import com.example.data.dto.Product


// Composable for catalogue card without icons
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CatalogueCard(product: Product, products: ProductState, navController : NavController) {
    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxHeight()
            .combinedClickable(
                onClick = { navController.navigate("Card/${product.id}") }
            )
           ) {
        Image(
            painterResource(id = R.drawable.food), modifier = Modifier.fillMaxWidth(),
            contentDescription = "")
        Column(modifier = Modifier
            .padding(12.dp)
            .weight(1f)) {
            CatalogueCardInfo(product = product, modifier = Modifier
                .size(12.dp)
                .weight(1f))
            if (products.shoppingCart.contains(product.id)) {
                PlusMinusButtons(products = products, product = product)
            } else {
                Button(
                    onClick = { products.shoppingCart.add(product.id) },
                    elevation = ButtonDefaults.buttonElevation(3.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .defaultMinSize(
                            minWidth = 5.dp,
                            minHeight = 5.dp
                        ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    ShowPrices(priceCurrent = product.priceCurrent, priceOld = product.priceOld)
                }
            }
        }
    }
}

