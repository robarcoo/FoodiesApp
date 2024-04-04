package com.example.shoppingcart

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.common.ProductState
import com.example.common.R
import com.example.data.dto.Product

@Composable
fun ShoppingCartItem(product : Product, products : ProductState) {
    Column {
        Row(modifier = Modifier
            .padding(16.dp)
            .height(130.dp)
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically) {
            Image(
                painterResource(R.drawable.food),
                contentDescription = null,
                modifier = Modifier
                    .height(96.dp)
                    .padding(end = 16.dp),
            )
            ShoppingCartItemText(product = product, products = products)
        }
        HorizontalDivider(color = Color(0xFFE0E0E0))
    }
}