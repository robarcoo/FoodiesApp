package com.example.splash
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.example.data.repository.Product
import com.example.data.repository.ProductsRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

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
        isAnimationFinished = AnimationLoader()
        if (isAnimationFinished) {
            navController.popBackStack()
            navController.navigate("Catalogue")
        }
//        val repository = ProductsRepositoryImpl()
//        val products = remember { mutableStateListOf<Product>() }
//        LaunchedEffect(key1 = true) {
//            products.swap(withContext(Dispatchers.IO) {
//                repository.getProducts()
//            })
//        }
    }
}

private fun <T> SnapshotStateList<T>.swap(withContext: List<T>) {
    this.clear()
    this.addAll(withContext)
}

@Composable
fun AnimationLoader() : Boolean {
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
