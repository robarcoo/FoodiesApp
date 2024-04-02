package com.example.splash
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

// Display splash screen and after animation has played will redirect to catalogue screen
@Composable
fun SplashScreen(navController: NavHostController) {
    var isAnimationFinished by remember { mutableStateOf(false) }
    val alpha = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        alpha.animateTo(
            1f,
            animationSpec = tween(2500)
        )
        delay(3000)
    }
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color(0xFFF15412))) {
        isAnimationFinished = animationLoader()
        if (isAnimationFinished) {
            LaunchedEffect(Unit) {
                navController.popBackStack()
                navController.navigate("Catalogue")
            }
        }
    }
}



