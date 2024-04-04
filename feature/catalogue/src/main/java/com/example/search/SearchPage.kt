package com.example.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.catalogue.InnerContent
import com.example.catalogue.R
import com.example.common.ProductState
import com.example.data.dto.Product
import com.example.utils.SearchData
import com.example.utils.swap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(products : ProductState, navController: NavController) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val result = remember { mutableStateListOf<Product>() }
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
                Icon(painter = painterResource(id = com.example.common.R.drawable.arrowleft),
                    contentDescription = "Go back",
                    tint = Color(0xFFF15412)
                )
            }
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    IconButton(onClick = { text = "" }) {
                        Icon(painter = painterResource(id = com.example.common.R.drawable.cancel),
                            contentDescription = "Go back",
                            tint = Color(0xFF666666)
                        )
                    }
                }
            },
            placeholder =  { Text(text = "Найти блюдо", color = Color(0xFF666666)) },
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
                    InnerContent(chunks = result.chunked(2), products, navController = navController)
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
