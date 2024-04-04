package com.example.catalogue


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.common.ProductState
import com.example.data.dto.Product
import com.example.filter.FilterDialog
import java.util.Locale

// Overall Catalogue Screen
@Composable
fun CatalogueScreen(products: ProductState, navController : NavController) {
    TopBarElement(products = products, navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarElement(products : ProductState, navController : NavController) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = {
            if (products.shoppingCart.isNotEmpty()) {
                BottomCartButton(products = products)
            }
        },
        topBar = {
            CenterAlignedTopAppBar(colors = TopAppBarDefaults.topAppBarColors(Color.White),
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo"
                    )
                },
                navigationIcon = {
                    Box(contentAlignment = Alignment.TopEnd) {
                        IconButton(onClick = { showBottomSheet = true }) {
                            Icon(
                                painter = painterResource(id = R.drawable.filter),
                                contentDescription = "Filter Food",
                                tint = Color.Black
                            )
                        }
                        if (products.appliedTags.size > 0) {
                            Text(text = products.appliedTags.size.toString(),
                                color = Color.White,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.drawBehind {
                                    drawCircle(
                                        color = Color(0xFFF15412),
                                        radius = 25f
                                    )
                                })
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("Search")
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "Search",
                            tint = Color.Black
                        )

                    }
                }

            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            CategoriesRow(products)
            if (showBottomSheet) {
                showBottomSheet = FilterDialog(products)
            }
        }


    }
}

@Composable
fun BottomCartButton(products: ProductState) {
    BottomAppBar() {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                    }) {
                    Icon(painter = painterResource(R.drawable.cart), contentDescription = "Add to cart", tint = Color.White)
                    Text(text = "${overallSum(products = products)}")
                }
        }
}

fun overallSum(products: ProductState) : Int {
    val allBought = products.shoppingCart.groupingBy { it }.eachCount()
    var sum = 0
    var inKopecks = 0
    allBought.forEach {bought ->
        inKopecks = (products.products.find { it.id == bought.key }?.priceCurrent) ?: 0
        sum += (inKopecks * bought.value / 100)
    }
    return sum
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(products : ProductState, navController: NavController) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val result = remember { mutableStateListOf<Product>()}
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
            SearchBar(modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 15.dp, spotColor = Color.Transparent),
                shape = RectangleShape,
                query = text, onQueryChange = { text = it }, onSearch = { active = false},
                active = active, onActiveChange = {active = it},
                leadingIcon = { IconButton(onClick = {
                    navController.popBackStack()
                    navController.navigate("Catalogue")
                }) {
                    Icon(painter = painterResource(id = R.drawable.arrowleft),
                        contentDescription = "Go back",
                        tint = Color(0xFFF15412))
                }},
                trailingIcon = {
                    if (text.isNotEmpty()) {
                        IconButton(onClick = { text = "" }) {
                            Icon(painter = painterResource(id = R.drawable.cancel),
                                contentDescription = "Go back",
                                tint = Color(0xFF666666))
                        }
                    }
                },
                placeholder =  { Text(text = "Найти блюдо", color = Color(0xFF666666))},
                colors = SearchBarDefaults.colors(containerColor = Color.White, dividerColor = Color.White,
                    inputFieldColors = TextFieldDefaults.colors(focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White, cursorColor = Color(0xFFF15412),
                        focusedTextColor = Color.Black, unfocusedTextColor = Color.Black))) {
                result.swap(SearchData(text, products))
                Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    if (result.size == 0) {
                        Text(text = "Ничего не нашлось :(", color = Color(0xFF666666), textAlign = TextAlign.Center)
                    }
                    else {
                        InnerContent(chunks = result.chunked(2), products)
                    }
                }
            }
        Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Введите название блюда, \n" +
                            "которое ищете", color = Color(0xFF666666), textAlign = TextAlign.Center
                )
        }


    }
}

private fun <T> SnapshotStateList<T>.swap(data: List<T>) {
    this.clear()
    this.addAll(data)

}


fun SearchData(query : String, products: ProductState) =
    products.products.filter { product -> product.name.lowercase(Locale.getDefault()).contains(query.lowercase(
        Locale.ROOT)) }










