package com.example.myshop.screens.shoppingCart;

import androidx.lifecycle.ViewModel
import com.example.myshop.data.ShoppingCartRepository

class ShoppingCartViewModel : ViewModel()  {

    val shoppingCart = ShoppingCartRepository.getCartProducts()

}
