package com.example.splash

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

// Load animation and checks if animation has ended
@Composable
fun animationLoader() : Boolean {
    var answer = false
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.foodies))
    val progress by animateLottieCompositionAsState(composition = composition)
    LottieAnimation(
        composition = composition,
        modifier = Modifier.size(400.dp),
        iterations = 1
    )
    if (progress == 1f) {
        answer = true
    }

    return answer
}
