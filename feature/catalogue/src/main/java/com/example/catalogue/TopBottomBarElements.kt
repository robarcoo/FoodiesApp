package com.example.catalogue


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.common.BottomBar
import com.example.common.ProductState
import com.example.filter.FilterDialog
import com.example.utils.overallSum


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBottomBarElements(products : ProductState, navController : NavController) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        bottomBar = {
            if (products.shoppingCart.isNotEmpty()) {
                BottomBar(text = " ${overallSum(products = products)} ₽",
                    painterResource(id = com.example.common.R.drawable.cart)) {
                    navController.navigate("ShoppingCart")
                }
            }
        },
        topBar = {
            CenterAlignedTopAppBar(colors = TopAppBarDefaults.topAppBarColors(Color.White),
                title = {
                    Image(
                        painter = painterResource(id = com.example.common.R.drawable.logo),
                        contentDescription = "Logo"
                    )
                },
                navigationIcon = {
                    Box(contentAlignment = Alignment.TopEnd) {
                        IconButton(onClick = { showBottomSheet = true }) {
                            Icon(
                                painter = painterResource(id = com.example.common.R.drawable.filter),
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
                            painter = painterResource(id = com.example.common.R.drawable.search),
                            contentDescription = "Search",
                            tint = Color.Black
                        )

                    }
                }

            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            CategoriesRow(products, navController)
            if (showBottomSheet) {
                showBottomSheet = FilterDialog(products)
            }
        }


    }
}



