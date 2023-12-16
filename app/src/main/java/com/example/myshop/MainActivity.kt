package com.example.myshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myshop.data.MyshopRepository
import com.example.myshop.screens.product_details.ProductDetailsScreen
import com.example.myshop.screens.product_details.ProductDetailsViewModel
import com.example.myshop.screens.product_list.ProductListScreen
import com.example.myshop.screens.product_list.ProductListViewModel
import com.example.myshop.ui.theme.MyshopTheme

class MainActivity : ComponentActivity() {
    private val _productListViewModel: ProductListViewModel by viewModels()
    private val _productDetailsViewModel: ProductDetailsViewModel by viewModels()
   // private val _shoppingCartListViewModel: ShoppingCartListViewModel by viewModels()
   // private val _historyViewModel: HistoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MyshopRepository.initializeDatabase(applicationContext)

        setContent {
            MyshopTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "productListScreen"
                ) {

                    // We add new destinations by using 'composable()', and give the destination a name ('route')
                    composable(
                        route = "productListScreen"
                    ) {
                        ProductListScreen(
                            viewModel = _productListViewModel,
                            onProductClick = { productId ->
                                navController.navigate("productDetailsScreen/$productId")
                            }
                        )
                    }

                    composable(
                        route = "productDetailsScreen/{productId}",
                        arguments = listOf(
                            navArgument(name = "productId") {
                                type = NavType.IntType
                            }
                        )
                    ) { backStackEntry ->
                        val productId = backStackEntry.arguments?.getInt("productId") ?: -1

                        // LaunchedEffect will run it's code block whenever 'astronautId' is updated
                        LaunchedEffect(productId) {
                            _productDetailsViewModel.setSelectedProduct(productId)
                        }

                        ProductDetailsScreen(
                            viewModel = _productDetailsViewModel,
                            onBackButtonClick = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}