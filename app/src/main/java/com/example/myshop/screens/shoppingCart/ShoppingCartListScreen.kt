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
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.myshop.components.TopBar
import com.example.myshop.data.History
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.UUID

@Composable
fun ShoppinCartListScreen(
    viewModel: ShoppingCartViewModel,
    navController: NavController,
) {
    val cartProducts = viewModel.cartProducts.collectAsState().value
    val totalPrice = viewModel.totalPrice.collectAsState().value  // This line will make the totalPrice update itself

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopBar(
            title = "Shopping Cart",
            onCartClick = {},
            onOptionsClick = {},
            onBackButtonClick = { navController.navigate("productListScreen") },
            navController = navController
        )
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(cartProducts) { cartProduct ->
                ShoppingCartItem(
                    cartProduct = cartProduct,
                    onRemoveClick = { viewModel.removeProductFromCart(cartProduct.id) }
                )
            }
        }
                Button(
                    onClick = {
                        val sharedId = UUID.randomUUID().toString()
                        val current = LocalDateTime.now()
                        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                        val formatted = current.format(formatter)

                        cartProducts.forEach { cartProduct ->
                            val history = History(
                                sharedId = sharedId,
                                productId = cartProduct.id,
                                purchaseDate = formatted,
                                productTitle = cartProduct.title,
                                quantity = cartProduct.quantity,
                                productPrice = cartProduct.price,
                                productCategory = cartProduct.category
                            )

                            viewModel.insertHistory(history)
                        }

                        viewModel.viewModelScope.launch {
                            viewModel.clearCart()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(10.dp)
                ) {
                    Text("Place order: $${"%.2f".format(totalPrice)}")
                }
    }
}