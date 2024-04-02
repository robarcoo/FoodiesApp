package com.example.catalogue

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

// Composable for calculations of prices
@Composable
fun ShowPrices(priceCurrent: Int, priceOld : Int?) {
    Text("${priceCurrent / 100} ₽", fontWeight = FontWeight.Bold,
        color = Color.Black)
    if (priceOld != null) {
        Text(
            text = "${priceOld / 100} ₽", style =
            TextStyle(
                color = Color(0xFF666666),
                textDecoration = TextDecoration.LineThrough
            )
        )
    }
}