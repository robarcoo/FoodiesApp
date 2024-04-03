package com.example.catalogue


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.data.repository.Product

// Overall Catalogue Screen
@Composable
fun CatalogueScreen(navController: NavController, products: List<Product>) {
    val chunks = products.chunked(2)
    TopBarElement(products = chunks)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarElement(products : List<List<Product>>) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { CenterAlignedTopAppBar(colors = TopAppBarDefaults.topAppBarColors(Color.White),
            title = { Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo")},
            navigationIcon = { IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.filter), contentDescription = "Filter Food", tint = Color.Black)
            }},
            actions = { IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.search), contentDescription = "Search", tint = Color.Black)

            }}

            )}) { innerPadding ->
        InnerContent(innerPadding = innerPadding, chunks  = products)

    }
}

@Composable
fun InnerContent(innerPadding : PaddingValues, chunks: List<List<Product>>) {
    LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(start = 12.dp, end = 12.dp, top = 0.dp, bottom = 0.dp),
            contentPadding = innerPadding
        ) {
            items(chunks) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)) {
                    it.forEach { product ->
                        CatalogueCardWithIcons(product, modifier = Modifier.weight(1f))
                    }
                }
            }
        }
}






