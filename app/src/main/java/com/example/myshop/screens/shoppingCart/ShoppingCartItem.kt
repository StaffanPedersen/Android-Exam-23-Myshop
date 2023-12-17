package com.example.myshop.screens.shoppingCart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myshop.data.CartProducts


@Composable
fun ShoppingCartItem  (
    cartProduct: CartProducts,
    onRemoveClick: () -> Unit = {}
) {

  //  val cartProducts = viewModel.cartProducts.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(2.dp))

            Row(
                modifier = Modifier
                    .padding(6.dp),
                horizontalArrangement = Arrangement.SpaceBetween
                //   .clickable {
                //      onClick() }
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(6.dp))
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.secondary)
                        .padding(6.dp)
                        .height(100.dp)
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(108.dp, 108.dp)
                            .background(color = Color.Gray),
                        model = cartProduct.image,
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        contentDescription = "Image of ${cartProduct.title}"
                    )
                    Column(
                        modifier = Modifier
                            //.fillMaxHeight()
                            .padding(start = 90.dp)
                            .absoluteOffset(30.dp, 0.dp)
                    ) {
                        Text(
                            text = cartProduct.title,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = cartProduct.category)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "Price: $${cartProduct.price}")
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Text(text = "In cart: $${cartProduct.quantity}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = onRemoveClick) {
                        Text("Remove")
                    }
                }
            }
        }
    }


//                Button(
//                    onClick = {
//                        viewModel.removeProductFromCart(cartProduct.id)
//                    }
//                ) {
//                    Text(text = "Remove")
//                }

