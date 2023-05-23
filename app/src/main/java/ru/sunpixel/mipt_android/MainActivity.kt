package ru.sunpixel.mipt_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.sunpixel.mipt_android.screen.LoginView
import ru.sunpixel.mipt_android.screen.LoginViewModel
import ru.sunpixel.mipt_android.screen.RestaurantDetailScreen
import ru.sunpixel.mipt_android.screen.RestaurantView
import ru.sunpixel.mipt_android.screen.RestaurantViewModel
import ru.sunpixel.mipt_android.ui.theme.Mipt_androidTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mipt_androidTheme {
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            val signInViewModel: LoginViewModel = viewModel()
                            LoginView(loginViewModel = signInViewModel, navController = navController)
                        }

                        composable("restaurants") {
                            val restaurantsViewModel: RestaurantViewModel = hiltViewModel<RestaurantViewModel>()

                            RestaurantView(restaurantViewModel = restaurantsViewModel,
                                navController = navController)
                        }

                        composable("detail/{name}") { backStackEntry ->
                            RestaurantDetailScreen(backStackEntry.arguments?.getString("name").orEmpty())
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Mipt_androidTheme {
        LoginView(viewModel(), rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
fun RestaurantPreview() {
    Mipt_androidTheme {
        RestaurantView(viewModel(), rememberNavController())
    }
}