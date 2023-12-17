package com.example.myshop.screens.shoppingCart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myshop.components.TopBar

@Composable
fun ShoppinCartListScreen(
    viewModel: ShoppingCartViewModel,
    onBackButtonClick: () -> Unit = {},
    navController: NavController,
    onProductClick: (productId: Int) -> Unit = {},

) {
    val cartProducts = viewModel.cartProducts.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            title = "Shopping Cart",
            onCartClick = {},
            onOptionsClick = {},
            onBackButtonClick = { onBackButtonClick() },
            navController = navController
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {  // Use LazyColumn instead of Column
            items(cartProducts) { cartProduct ->
                ShoppingCartItem(
                    cartProduct = cartProduct,
                    onRemoveClick = { viewModel.removeProductFromCart(cartProduct.id) }
                )
            }
        }
    }
}