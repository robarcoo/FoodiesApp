package com.example.catalogue

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.ProductState
import com.example.data.dto.Product

@Composable
fun InnerContent(chunks: List<List<Product>>, products : ProductState, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 12.dp, end = 12.dp, top = 0.dp, bottom = 0.dp),
    ) {
        items(chunks) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)) {
                it.forEach { product ->
                    CatalogueCardWithIcons(product, modifier = Modifier.weight(1f), products, navController = navController)
                }
            }
        }
    }
}