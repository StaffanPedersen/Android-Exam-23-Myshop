package com.example.myshop.screens.product_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel,
    onBackButtonClick: () -> Unit = {}
) {
    val productState = viewModel.selectedProduct.collectAsState()
    val product = productState.value
    if (product == null) {
        Text(text = "Failed to get details.")
        return
    }
//    AsyncImage(
//        modifier = Modifier
//            .fillMaxSize(),
//           // .background(color = Color.Gray),
//        model = product.image,
//        contentScale = ContentScale.Crop,
//        alignment = Alignment.Center,
//        contentDescription = "Image of ${product.title}"




    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorScheme.background),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onBackButtonClick() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "return button"
                )
            }
            Text(
                modifier = Modifier.padding(8.dp),
                text = product.title,
                style = MaterialTheme.typography.titleLarge
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 24.dp)
                .verticalScroll(state = rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(32.dp))
           Row {

           }
            AsyncImage(
                modifier = Modifier
                    .size(108.dp, 108.dp)
                    .background(color = Color.Gray),
                model = product.image,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                contentDescription = "Image of ${product.title}"
            )

//            Icon(
//                imageVector =  Icons.Default.FavoriteBorder,
//                contentDescription = null,
//                modifier = Modifier.clickable {
//
//                },
//                tint = Color.Red
//            )
            Text(
                text = product.title,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Description: ${product.description}",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Price: ${product.price}",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Customer rating: ${product.rating.rate}",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Customer rate count: ${product.rating.count}",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))



            Spacer(modifier = Modifier.height(32.dp))

//            Text(
////                modifier = Modifier.fillMaxWidth(),
////                text = product.description,
////                style = MaterialTheme.typography.bodyLarge,
////                color = Color.White
////            )

       // add to shoppingcart button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier
                        .height(48.dp)
                        .weight(1f),

                    onClick = {  }
                ) {
                    Text(
                        text = "Add to shopping cart",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )

                }
            }
        }
    }
}


