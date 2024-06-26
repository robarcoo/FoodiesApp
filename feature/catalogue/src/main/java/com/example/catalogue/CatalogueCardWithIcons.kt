package com.example.catalogue

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.ProductState
import com.example.data.dto.Product

// Full catalogue card with all icons
// If more than one, then icons will stand next to each in a row

@Composable
fun CatalogueCardWithIcons(product: Product, modifier: Modifier, products : ProductState, navController : NavController) {
    Box(modifier = modifier) {
        CatalogueCard(product = product, products = products, navController = navController)
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 17.dp, start = 12.dp, end = 0.dp, bottom = 0.dp)) {
            if (product.priceOld != null) {
                CardIcon(imageId = com.example.common.R.drawable.sale_foreground)
            }
            if (product.tagIds.contains(2)) {
                CardIcon(imageId = com.example.common.R.drawable.vegetarian_foreground)
            }
            if (product.tagIds.contains(4)) {
                CardIcon(imageId = com.example.common.R.drawable.spicy_foreground)
            }
        }

    }
}
