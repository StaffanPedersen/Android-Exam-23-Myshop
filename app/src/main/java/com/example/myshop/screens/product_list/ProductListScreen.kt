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
import androidx.navigation.NavController
import com.example.myshop.components.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    viewModel: ProductListViewModel,
    onProductClick: (productId: Int) -> Unit = {},
    onCartClick: () -> Unit = {},
    navController: NavController
) {
    val product = viewModel.product.collectAsState()
    val filterMensClothingOnly = viewModel.filterMensClothing.collectAsState()
    val filterElectronicsOnly = viewModel.filterElectronics.collectAsState()
    val filterWomenClothingOnly = viewModel.filterWomenClothing.collectAsState()
    val filterJeweleryOnly = viewModel.filterJewelery.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(
            title = "My Shop",
            onCartClick = onCartClick,
            onOptionsClick = {},
            navController = navController
        )
        Divider()
        Column (
            modifier = Modifier
                   .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilterChip(
                    selected = filterElectronicsOnly.value,
                    onClick = {
                        viewModel.setProductFilter(electronicsOnly = !filterElectronicsOnly.value)
                    },
                    label = { Text(text = "Electronics") }
                )
                Spacer(modifier = Modifier.width(8.dp))
                FilterChip(
                    selected = filterMensClothingOnly.value,
                    onClick = {
                        viewModel.setProductFilter(mensOnly = !filterMensClothingOnly.value)
                    },
                    label = { Text(text = "Men's clothing") }
                )
            }
            Row(                modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                FilterChip(
                    selected = filterWomenClothingOnly.value,
                    onClick = {
                        viewModel.setProductFilter(womenOnly = !filterWomenClothingOnly.value)
                    },
                    label = { Text(text = "Women's clothing") }
                )
                Spacer(modifier = Modifier.width(8.dp))
                FilterChip(
                    selected = filterJeweleryOnly.value,
                    onClick = {
                        viewModel.setProductFilter(jeweleryOnly = !filterJeweleryOnly.value)
                    },
                    label = { Text(text = "jewelery") }
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
}