package com.example.shoppingcart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.common.PlusMinusButtons
import com.example.common.ProductState
import com.example.common.ShowPrices
import com.example.data.dto.Product

@Composable
fun ShoppingCartItemText(products : ProductState, product: Product) {
    Column() {
        Row(modifier = Modifier.padding(bottom = 12.dp)) {
            Text(product.name, color = Color.Black)

        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(modifier = Modifier.width(135.dp)) {
                PlusMinusButtons(products, product, Color(0xFFF5F5F5))
            }
            Spacer(modifier = Modifier.weight(1f))
            Column() {
                val count =
                    products.shoppingCart.count { it == product.id }
                ShowPrices(
                    product.priceCurrent * count,
                    product.priceOld?.times(count)
                )
            }
        }
    }
}