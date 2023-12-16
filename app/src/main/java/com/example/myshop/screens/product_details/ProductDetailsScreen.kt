package com.example.myshop.screens.product_details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

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




}