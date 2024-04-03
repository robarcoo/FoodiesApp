package com.example.catalogue


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.data.repository.Product

// Overall Catalogue Screen
@Composable
fun CatalogueScreen(products: List<Product>) {
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
        Column(modifier = Modifier.padding(innerPadding)) {
            CategoriesRow()
            InnerContent(chunks = products)
        }


    }
}

@Composable
@Preview
fun CategoriesRow() {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Home", "About", "Settings", "More", "Something", "Everything")
    Column(modifier = Modifier) {
        ScrollableTabRow(selectedTabIndex = tabIndex,  edgePadding = 7.dp, containerColor = Color.White, modifier = Modifier.background(Color.White).padding(8.dp),
            indicator = {
            }, divider = {
            }) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(text = title, color = if (tabIndex == index) { Color.White } else { Color.Black}, fontWeight = FontWeight.Bold)},
                    modifier = Modifier.height(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (tabIndex == index) { Color(0xFFF15412) } else { Color.White}),
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    selectedContentColor = Color(0xFFF15412))
            }

        }
    }
}

@Composable
fun InnerContent(chunks: List<List<Product>>) {
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
                        CatalogueCardWithIcons(product, modifier = Modifier.weight(1f))
                    }
                }
            }
        }
}






