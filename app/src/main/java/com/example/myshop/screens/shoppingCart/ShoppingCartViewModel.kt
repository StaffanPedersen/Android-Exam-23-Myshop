package com.example.myshop.screens.shoppingCart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.data.CartProducts
import com.example.myshop.data.ShoppingCartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShoppingCartViewModel: ViewModel() {
    private val _cartProducts = MutableStateFlow<List<CartProducts>>(emptyList())
    val cartProducts: StateFlow<List<CartProducts>> = _cartProducts

    init {
        fetchCartProducts()
    }

    private fun fetchCartProducts() {
        viewModelScope.launch {
            ShoppingCartRepository.getAllCartProducts().collect { products ->
                _cartProducts.value = products
            }
        }
    }

    fun removeProductFromCart(productId: Int) {
        viewModelScope.launch {
            ShoppingCartRepository.removeProductFromCart(productId)
        }
    }
}