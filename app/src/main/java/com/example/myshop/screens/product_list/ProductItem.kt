package com.example.myshop.screens.product_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.myshop.data.Products

@Composable
fun ProductItem(
    product: Products,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                onClick() }
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.secondary)
                .padding(6.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(108.dp, 108.dp)
                    .background(color = Color.Gray),
                model = product.image,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                contentDescription = "Image of ${product.title}"
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 90.dp)
                    .absoluteOffset(30.dp, 0.dp)
            ) {
                Text(text = product.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text =  product.category)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Price: $${product.price}")
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
