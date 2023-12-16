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
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myshop.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel,
    onProductClick: (productId: Int) -> Unit = {}
) {

    val product = viewModel.product.collectAsState()

    val filterMensClothingOnly = viewModel.filterMensClothing.collectAsState()
    val filterElectronicsOnly = viewModel.filterElectronics.collectAsState()
    val filterWomensClothingOnly = viewModel.filterWomensClothing.collectAsState()



        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopBar(
                title = "My Shop",
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
            FilterChip(
                selected = filterElectronicsOnly.value,
                onClick = {
                    viewModel.setProductFilter(activeOnly = !filterElectronicsOnly.value)
                },
                label = { Text(text = "Electronics") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            FilterChip(
                selected = filterMensClothingOnly.value,
                onClick = {
                    viewModel.setProductFilter(inSpaceOnly = !filterMensClothingOnly.value)
                },
                label = { Text(text = "Men's clothing") }
            )
            Spacer(modifier = Modifier.width(8.dp))
            FilterChip(
                selected = filterWomensClothingOnly.value,
                onClick = {
                    viewModel.setProductFilter(favoritesOnly = !filterWomensClothingOnly.value)
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