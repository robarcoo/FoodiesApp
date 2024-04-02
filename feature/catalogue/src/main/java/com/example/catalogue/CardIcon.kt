package com.example.catalogue

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

// Composable for icons shown above card (sale, vegetarian food, spicy food)
@Composable
fun CardIcon(imageId: Int) {
    Image(
        painter = painterResource(id = imageId),
        contentDescription = null,
        modifier = Modifier
            .size(35.dp)
            .padding(top = 0.dp, start = 0.dp, end = 6.dp, bottom = 0.dp)
    )
}