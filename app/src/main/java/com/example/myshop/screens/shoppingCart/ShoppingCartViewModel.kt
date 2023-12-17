package com.example.myshop.screens.shoppingCart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.data.CartProducts
import com.example.myshop.data.ShoppingCartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ShoppingCartViewModel : ViewModel() {

    private var setProductToCart: StateFlow<CartProducts?> = MutableStateFlow(null)


    fun setProductToCart(products: List<CartProducts>) {
        viewModelScope.launch {
            val cartProducts = ShoppingCartRepository.insertCartProducts(products)

        }
    }
}