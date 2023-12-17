package com.example.myshop.screens.product_details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myshop.components.TopBar
import com.example.myshop.data.CartProducts
import com.example.myshop.screens.shoppingCart.ShoppingCartViewModel

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel,
    onBackButtonClick: () -> Unit = {},
    onCartClick: () -> Unit = {},
    navController: NavController


) {
    val productState = viewModel.selectedProduct.collectAsState()
    val product = productState.value
    val shoppingCartViewModel = ShoppingCartViewModel()
    if (product == null) {
        Text(text = "Failed to get details.")
        return
    }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorScheme.background),
        ) {
            TopBar(
                title = "Products",
                onCartClick = onCartClick,
                onOptionsClick = { /* Handle options click here */ },
                onBackButtonClick =  { onBackButtonClick()},
                navController = navController
            )

            Divider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {

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
                    .padding(horizontal = 14.dp)
                    .verticalScroll(state = rememberScrollState()),
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                )
                {


                    AsyncImage(
                        modifier = Modifier
                            .width(300.dp)
                            .height(300.dp)
                            .size(108.dp, 108.dp)
                            .background(color = Color.Gray),
                        model = product.image,
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        contentDescription = "Image of ${product.title}"
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))

                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.clickable {

                    }
                )
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .shadow(1.dp),

                    text = "Description: ${product.description}",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Medium,
                )



                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Customer rating: ${product.rating.rate}",
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Customer rate count: ${product.rating.count}",

                )
                Spacer(modifier = Modifier.height(8.dp))

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Price: $${product.price}",
                    )
                Spacer(modifier = Modifier.height(20.dp))


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // shoppingcart button
                    Button(
                        onClick = { shoppingCartViewModel.setProductToCart( listOf(CartProducts(product.id, product.title, product.price,product.description, product.category, product.image, 1 )))},
                        contentPadding = ButtonDefaults.ButtonWithIconContentPadding
                    ) {
                        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                        Text("Add to shopping cart")
                    }
                }
            }
        }
    }


