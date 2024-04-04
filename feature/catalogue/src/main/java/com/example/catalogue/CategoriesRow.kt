package com.example.catalogue

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.common.ProductState
import com.example.utils.filterProducts

@Composable
fun CategoriesRow(products: ProductState, navController: NavController) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = products.categories
    Column(modifier = Modifier) {
        ScrollableTabRow(selectedTabIndex = tabIndex,  edgePadding = 7.dp, containerColor = Color.White, modifier = Modifier
            .background(Color.White)
            .padding(8.dp),
            indicator = {
            }, divider = {
            }) {
            tabs.forEachIndexed { index, category ->
                Tab(text = { Text(text = category.name, color = if (tabIndex == index) { Color.White } else { Color.Black}, fontWeight = FontWeight.Bold) },
                    modifier = Modifier
                        .height(50.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            if (tabIndex == index) {
                                Color(0xFFF15412)
                            } else {
                                Color.White
                            }
                        ),
                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    selectedContentColor = Color(0xFFF15412)
                )
            }

        }

        val categoryProducts = products.categoriesWithProducts[tabIndex]
        val withFilters = filterProducts(products, categoryProducts).chunked(2)
        if (withFilters.isNotEmpty()) {
            InnerContent(chunks = withFilters, products, navController)
        } else {
            if (products.appliedTags.size > 0) {
                NoResultsPage("Таких блюд нет :(\n" +
                        "Попробуйте изменить фильтры")
            } else {
                NoResultsPage("Cюда пока ничего не завезли")
            }
        }
    }
}
