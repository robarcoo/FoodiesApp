package com.example.foodiesapp

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.card.CardScreen
import com.example.catalogue.CatalogueScreen
import com.example.common.SharedViewModel
import com.example.foodiesapp.ui.theme.FoodiesAppTheme
import com.example.search.SearchPage
import com.example.shoppingcart.ShoppingCartScreen
import com.example.splash.SplashScreen
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

}
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: SharedViewModel by viewModels()
        setContent {
            val navController = rememberNavController()
            val products by viewModel.productState.collectAsState()
            FoodiesAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "Splash") {
                        composable("Splash") {
                            SplashScreen(navController = navController)
                        }
                        composable("Catalogue") {
                            CatalogueScreen(products, navController)
                        }
                        composable("Search") {
                            SearchPage(products, navController)
                        }
                        composable(route = "Card/{id}",
                            arguments = listOf(navArgument("id") {
                                type = NavType.StringType
                            })
                            ) {
                            val id = it.arguments?.getString("id")?.toIntOrNull() ?: -1
                            CardScreen(navController, products, id)
                        }
                        composable("ShoppingCart") {
                            ShoppingCartScreen(products, navController)
                        }

                    }
                }
            }
        }
    }
}
