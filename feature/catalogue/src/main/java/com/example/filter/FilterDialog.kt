package com.example.filter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.common.ProductState
import com.example.utils.ApplyTags
import kotlinx.coroutines.launch

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