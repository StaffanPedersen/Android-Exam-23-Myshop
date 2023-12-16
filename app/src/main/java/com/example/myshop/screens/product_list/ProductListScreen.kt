package com.example.myshop.screens.product_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel,
    onProductClick: (productId: Int) -> Unit = {}
) {
    val product = viewModel.product.collectAsState()

    val filterInSpaceOnly = viewModel.filterMensClothing.collectAsState()
    val filterActiveOnly = viewModel.filterElectronics.collectAsState()
    val filterFavoriteOnly = viewModel.filterWomensClothing.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "Products",
                style = MaterialTheme.typography.titleLarge
            )
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { }
                ) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Shopping cart"
                    )
                }
                IconButton(
                    onClick = {  }
                ) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Options"
                    )
                }
            }
        }

        Divider()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FilterChip(
                selected = filterActiveOnly.value,
                onClick = {
                    viewModel.setAstronautFilter(activeOnly = !filterActiveOnly.value)
                },
                label = { Text(text = "Electronics") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            FilterChip(
                selected = filterInSpaceOnly.value,
                onClick = {
                    viewModel.setAstronautFilter(inSpaceOnly = !filterInSpaceOnly.value)
                },
                label = { Text(text = "Men's clothing") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            FilterChip(
                selected = filterFavoriteOnly.value,
                onClick = {
                    viewModel.setAstronautFilter(favoritesOnly = !filterFavoriteOnly.value)
                },
                label = { Text(text = "Women's clothing") }
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(product.value) { product ->
                ProductItem(
                    product = product,
                    onClick = {
                        onProductClick(product.id)
                    }
                )
            }
        }
    }
}