package com.example.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.dto.Product

@Composable
fun CardBottomBar(text: String, Click : () -> Unit) {
    BottomAppBar(containerColor = Color.White, modifier = Modifier.navigationBarsPadding()) {
        Column(modifier = Modifier
            .navigationBarsPadding()
            .height(150.dp)
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .background(Color.White)) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFF15412)),
                shape = RoundedCornerShape(8.dp),
                onClick = Click
            ) {
                Text(
                    text = text, fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}