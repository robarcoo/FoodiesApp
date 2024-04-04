package com.example.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.dto.Product

@Composable
fun PlusMinusButtons(products: ProductState, product : Product, colors : Color = Color.White) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { products.shoppingCart.remove(product.id) },
            colors = ButtonDefaults.buttonColors(colors),
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = if (colors == Color.White) { 3.dp } else { 0.dp }
            ),
            modifier = Modifier.defaultMinSize(
                minWidth = 10.dp,
                minHeight = 10.dp
            )
                .width(50.dp).height(50.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.minus),
                contentDescription = "Remove from cart",
                tint = Color(0xFFF15412)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = products.shoppingCart.count { it == product.id }.toString(),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 6.dp)
        )
        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { products.shoppingCart.add(product.id) },
            colors = ButtonDefaults.buttonColors(colors),
            shape = RoundedCornerShape(10.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = if (colors == Color.White) { 3.dp } else { 0.dp }
            ),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.defaultMinSize(
                minWidth = 10.dp,
                minHeight = 10.dp
            )
                .width(50.dp).height(50.dp),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "Add to cart",
                tint = Color(0xFFF15412)
            )
        }
    }
}