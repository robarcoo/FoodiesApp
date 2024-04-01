package com.example.foodiesapp

import android.content.Context
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.splash.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController, context: MainActivity) {
    val alpha = remember {
        Animatable(0f)
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
        AnimationLoader()
    }
}

@Composable
fun AnimationLoader() {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.foodies))

    LottieAnimation(
        composition = composition, iterations = 1,
        modifier = Modifier.size(400.dp)
    )
}

private fun onBoardingIsFinished(context: MainActivity): Boolean {
    val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("isFinished", false)

}