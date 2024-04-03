package com.example.catalogue

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.data.dto.Product


// Composable for catalogue card without icons
@Composable
fun CatalogueCard(product: Product) {
    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxHeight()) {
        Image(
            painterResource(id = R.drawable.food), modifier = Modifier.fillMaxWidth(),
            contentDescription = "")
        Column(modifier = Modifier
            .padding(12.dp)
            .weight(1f)) {
            CatalogueCardInfo(product = product, modifier = Modifier
                .size(12.dp)
                .weight(1f))
            Button(
                onClick = {},
                elevation = ButtonDefaults.buttonElevation(3.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                ShowPrices(priceCurrent = product.priceCurrent, priceOld = product.priceOld)
            }
        }
    }
}