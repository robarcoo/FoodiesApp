package com.example.shoppingcart

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingCartTopBar(navController: NavController) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(Color.White),
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
                navController.navigate("Catalogue")})
            {
                Icon(painter = painterResource(R.drawable.arrowleft), contentDescription = null,
                    tint = Color(0xFFF15412)
                )
            }
        }, title =  { Text("Корзина", fontWeight = FontWeight.Bold, color = Color.Black) } )
}
