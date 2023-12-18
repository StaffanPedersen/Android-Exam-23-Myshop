package com.example.myshop.screens.shoppingCart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.data.dataClass.CartProducts
import com.example.myshop.data.dataClass.History
import com.example.myshop.data.repository.HistoryRepository
import com.example.myshop.data.repository.ShoppingCartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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
    val totalPrice = _cartProducts.map { products ->
        products.sumOf { it.price * it.quantity }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), 0.0)
    fun removeProductFromCart(productId: Int) {
        viewModelScope.launch {
            ShoppingCartRepository.removeProductFromCart(productId)
        }
    }
    fun insertHistory(history: History) {
        viewModelScope.launch {
            HistoryRepository.insertHistory(history)
        }
    }
    suspend fun clearCart() {
        viewModelScope.launch {
            ShoppingCartRepository.clearCart()
        }
    }
}