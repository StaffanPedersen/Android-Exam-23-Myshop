package com.example.myshop.screens.product_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.data.CartProducts
import com.example.myshop.data.MyShopRepository
import com.example.myshop.data.Products
import com.example.myshop.data.ShoppingCartRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductDetailsViewModel : ViewModel() {
    var selectedProduct: StateFlow<Products?> = MutableStateFlow(null)
        private set
    fun setSelectedProduct(productId: Int) {
        selectedProduct = MyShopRepository.getProductById(productId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = null
            )
    }
    fun addProductToCart(product: Products) {
        viewModelScope.launch {
            val existingProduct = ShoppingCartRepository.getCartProductById(product.id)

            if (existingProduct != null) {
                existingProduct.quantity += 1
                ShoppingCartRepository.updateCartProduct(existingProduct)
            } else {
                val cartProduct = CartProducts(
                    id = product.id,
                    title = product.title,
                    price = product.price,
                    description = product.description,
                    category = product.category,
                    image = product.image,
                    quantity = 1
                )
                ShoppingCartRepository.insertCartProducts(listOf(cartProduct))
            }
        }
    }
}