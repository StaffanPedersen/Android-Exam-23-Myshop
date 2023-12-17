package com.example.myshop.screens.shoppingCart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myshop.data.CartProducts


@Composable
fun ShoppingCartItem(
    cartProduct: CartProducts,
    onRemoveClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp, 100.dp)
                .background(color = Color.Gray),
            model = cartProduct.image,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            contentDescription = "Image of ${cartProduct.title}"
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(start = 8.dp)
        ) {
            Text(
                maxLines = 1,
                text = cartProduct.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = cartProduct.category)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(text = "In cart: ${cartProduct.quantity}")
            Button(
                onClick = onRemoveClick,
                modifier = Modifier
                    .padding(5.dp)
                    .height(50.dp)
                    .width(150.dp)
            ) {
                Text(
                    text = "X",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp)
                )
            }
        }
    }
}