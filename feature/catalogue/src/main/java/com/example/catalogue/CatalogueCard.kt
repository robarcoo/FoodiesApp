package com.example.catalogue

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.card.CardScreen
import com.example.common.ProductState
import com.example.data.dto.Product


// Composable for catalogue card without icons
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CatalogueCard(product: Product, products: ProductState, navController : NavController) {
    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxHeight()
            .combinedClickable(
                onClick = { navController.navigate("Card/${product.id}") }
            )
           ) {
        Image(
            painterResource(id = R.drawable.food), modifier = Modifier.fillMaxWidth(),
            contentDescription = "")
        Column(modifier = Modifier
            .padding(12.dp)
            .weight(1f)) {
            CatalogueCardInfo(product = product, modifier = Modifier
                .size(12.dp)
                .weight(1f))
            if (products.shoppingCart.contains(product.id)) {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                        Button(
                            onClick = { products.shoppingCart.remove(product.id) },
                            colors = ButtonDefaults.buttonColors(Color.White),
                            contentPadding = PaddingValues(0.dp),
                            shape = RoundedCornerShape(10.dp),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 3.dp
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
                    Text(text = products.shoppingCart.count { it == product.id }.toString(), fontWeight = FontWeight.Bold,
                        color = Color.Black, modifier = Modifier.padding(horizontal = 6.dp))
                    Spacer(modifier = Modifier.weight(1f))

                        Button(
                            onClick = { products.shoppingCart.add(product.id) },
                            colors = ButtonDefaults.buttonColors(Color.White),
                            shape = RoundedCornerShape(10.dp),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 3.dp
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
            } else {
                Button(
                    onClick = { products.shoppingCart.add(product.id) },
                    elevation = ButtonDefaults.buttonElevation(3.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.fillMaxWidth().height(50.dp).defaultMinSize(
                        minWidth = 5.dp,
                        minHeight = 5.dp
                    ),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    ShowPrices(priceCurrent = product.priceCurrent, priceOld = product.priceOld)
                }
            }
        }
    }
}