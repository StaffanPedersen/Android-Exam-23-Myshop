package com.example.myshop.screens.ShoppingCart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myshop.components.TopBar


@Composable
fun ShoppinCartListScreen(
// viewModel: ShoppinCartListViewModel,
//onProductClick: (productId: Int) -> Unit = {}
) {


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            title = "Shopping Cart",
            onCartClick = { /* Handle cart click here */ },
            onOptionsClick = { /* Handle options click here */ }
        )

        Divider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

        }

//        LazyColumn(
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f)
//        ) {
//            items(product.value) { product ->
//                ProductItem(
//                    product = product,
//                    onClick = {
//                        onProductClick(product.id)
//                    }
//                )
//            }
//        }
    }
}