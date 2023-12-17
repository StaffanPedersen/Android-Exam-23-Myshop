package com.example.myshop.screens.shoppingCart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myshop.components.TopBar

@Composable
fun ShoppinCartListScreen(
    viewModel: ShoppingCartViewModel,
    onBackButtonClick: () -> Unit = {},
    navController: NavController,
    onProductClick: (productId: Int) -> Unit = {}
) {
    val cartProducts = viewModel.cartProducts.collectAsState().value
    val totalPrice = viewModel.totalPrice.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(
            title = "Shopping Cart",
            onCartClick = {},
            onOptionsClick = {},
            onBackButtonClick = { onBackButtonClick() },
            navController = navController
        )

        LazyColumn(
            modifier = Modifier.weight(1f)  // This will make the LazyColumn take up all available space
        ) {
            items(cartProducts) { cartProduct ->
                ShoppingCartItem(
                    cartProduct = cartProduct,
                    onRemoveClick = { viewModel.removeProductFromCart(cartProduct.id) }
                )
            }
        }

        Button(
            onClick = { /* Handle place order click */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(10.dp)
        ) {
            Text("Place order: $${"%.2f".format(totalPrice)}")
        }
    }
}