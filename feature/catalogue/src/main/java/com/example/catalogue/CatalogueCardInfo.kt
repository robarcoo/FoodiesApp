package com.example.catalogue

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.data.dto.Product


// Composable for text below product image and above button
@Composable
fun CatalogueCardInfo(product: Product, modifier: Modifier) {
    Text(product.name, color = Color.Black)
    Spacer(modifier = Modifier.size(4.dp))
    Text("${product.measure} ${product.measureUnit}", color = Color(0xFF666666))
    Spacer(modifier = modifier)
}
