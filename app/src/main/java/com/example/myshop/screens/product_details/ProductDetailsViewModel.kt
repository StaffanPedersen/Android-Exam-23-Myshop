package com.example.myshop.screens.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.data.MyshopRepository
import com.example.myshop.data.Products
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class ProductDetailsViewModel : ViewModel() {
    var selectedProduct: StateFlow<Products?> = MutableStateFlow(null)
        private set

    fun setSelectedProduct(productId: Int) {
        selectedProduct = MyshopRepository.getProductById(productId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = null
            )
    }

}