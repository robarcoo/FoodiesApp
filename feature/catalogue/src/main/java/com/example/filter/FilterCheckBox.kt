package com.example.filter

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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

