package com.example.catalogue


import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.ProductState
import com.example.data.dto.Product
import kotlinx.coroutines.launch

// Overall Catalogue Screen
@Composable
fun CatalogueScreen(products: ProductState) {
    TopBarElement(products = products)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarElement(products : ProductState) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { CenterAlignedTopAppBar(colors = TopAppBarDefaults.topAppBarColors(Color.White),
            title = { Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo")},
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
            }},
            actions = { IconButton(onClick = { /*TODO*/ }) {
                Icon(painter = painterResource(id = R.drawable.search), contentDescription = "Search", tint = Color.Black)

            }}

            )}) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            CategoriesRow(products)
            if (showBottomSheet) {
                showBottomSheet = FilterDialog(products)
            }
        }


    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterDialog(products: ProductState): Boolean {
    val result = remember { mutableStateOf(true) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val withVegetarian = remember { mutableStateOf(products.appliedTags.contains(2)) }
    val withSpicy = remember { mutableStateOf(products.appliedTags.contains(4)) }
    val withSale = remember { mutableStateOf(products.appliedTags.contains(6)) }

    ModalBottomSheet(
        onDismissRequest = {
            result.value = false
        },
        sheetState = sheetState,
        shape = RoundedCornerShape(24.dp),
        containerColor = Color.White
    ) {
        Column (modifier = Modifier.padding(start = 25.dp, end = 25.dp, bottom = 25.dp, top = 0.dp)){
            Text(text = "Подобрать блюда", fontSize = 20.sp, fontWeight = FontWeight.Bold,
                color = Color.Black, modifier = Modifier.padding(bottom = 10.dp))
            withVegetarian.value = filterCheckBox(withVegetarian.value, "Без мяса")

            HorizontalDivider(color = Color(0xFFE0E0E0))
            withSpicy.value = filterCheckBox(withSpicy.value, "Острое")

            HorizontalDivider(color = Color(0xFFE0E0E0))
            withSale.value = filterCheckBox(withSale.value, "Со скидкой")

            Spacer(modifier = Modifier.size(10.dp))
            Button(modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFF15412)),
                onClick = {
                scope.launch {
                        ApplyTags(products, withVegetarian.value,  2)
                        ApplyTags(products, withSpicy.value,  4)
                        ApplyTags(products, withSale.value,  6)
                        result.value = false
                    }
            }) {
                Text(text ="Готово", color = Color.White)
            }
        }
    }
    return result.value
}

fun ApplyTags(products: ProductState, isApplied : Boolean, tag : Int) {
    if (isApplied) {
        if (!products.appliedTags.contains(tag)) {
            products.appliedTags.add(tag)
        }
    } else {
        products.appliedTags.remove(tag)
    }
}
@Composable
fun filterCheckBox(isChecked: Boolean, text: String) : Boolean {
    val result = remember { mutableStateOf(isChecked) }
    Row(modifier = Modifier.padding(vertical = 6.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(text, color = Color.Black)
        Spacer(modifier = Modifier.weight(1f))
        Checkbox(
            checked = isChecked,
            onCheckedChange = { result.value = it },
            colors = CheckboxDefaults.colors(Color(0xFFF15412), checkmarkColor = Color.White)
        )
    }
    return result.value
}

@Composable
fun CategoriesRow(products: ProductState) {
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
                Tab(text = { Text(text = category.name, color = if (tabIndex == index) { Color.White } else { Color.Black}, fontWeight = FontWeight.Bold)},
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
                    selectedContentColor = Color(0xFFF15412))
            }

        }

        val categoryProducts = products.categoriesWithProducts[tabIndex]
        val withFilters = filterProducts(products, categoryProducts).chunked(2)
        if (withFilters.isNotEmpty()) {
            InnerContent(chunks = withFilters)
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

fun filterProducts(product: ProductState, chunk : List<Product>) : List<Product> {
    var result = chunk
    product.appliedTags.forEach {
        result = when(it) {
            2 -> result.filter { product -> product.tagIds.contains(2) }
            4 -> result.filter { product -> product.tagIds.contains(4) }
            6 -> result.filter { product -> product.priceOld != null }
            else -> result
        }
    }
    return result
}

@Composable
fun NoResultsPage(line : String) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(text = line, color = Color(0xFF666666), textAlign = TextAlign.Center)

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






