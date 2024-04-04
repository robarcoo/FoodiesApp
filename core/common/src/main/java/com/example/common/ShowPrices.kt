package com.example.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

// Composable for calculations of prices
@Composable
fun ShowPrices(priceCurrent: Int, priceOld : Int?) {
    Text("${priceCurrent / 100} ₽ ", fontWeight = FontWeight.Bold,
        color = Color.Black, fontSize = 16.sp)
    if (priceOld != null) {
        Text(
            text = "${priceOld / 100} ₽", fontSize = 16.sp, style =
            TextStyle(
                color = Color(0xFF666666),
                textDecoration = TextDecoration.LineThrough
            )
        )
    }
}