package com.example.myshop.screens.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.data.MyShopRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class ProductListViewModel : ViewModel() {
    private val _filterMensClothing = MutableStateFlow(false)
    val filterMensClothing = _filterMensClothing.asStateFlow()

    private val _filterElectronics = MutableStateFlow(false)
    val filterElectronics = _filterElectronics.asStateFlow()

    private val _filterWomenClothing = MutableStateFlow(false)
    val filterWomenClothing = _filterWomenClothing.asStateFlow()

    private val _filterJewelery = MutableStateFlow(false)
    val filterJewelery = _filterJewelery.asStateFlow()

    val product = combine(
        MyShopRepository.getProduct(),
        filterMensClothing,
        filterElectronics,
        filterWomenClothing,
        filterJewelery
    ) { productList, mensClothing, electronics, womenClothing, jewelery ->
        productList
            .filter { !mensClothing || it.category == "men's clothing"  }
            .filter { !electronics || it.category == "electronics"  }
            .filter { !womenClothing || it.category == "women's clothing" }
            .filter { !jewelery || it.category == "jewelery" }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList()
    )

    fun setProductFilter(
        mensOnly: Boolean? = null,
        electronicsOnly: Boolean? = null,
        womenOnly: Boolean? = null,
        jeweleryOnly: Boolean? = null
    ) {
        mensOnly?.let {
            _filterMensClothing.value = it
        }
        electronicsOnly?.let {
            _filterElectronics.value = it
        }
        womenOnly?.let {
            _filterWomenClothing.value = it
        }
        jeweleryOnly?.let {
            _filterJewelery.value = it
        }
    }
}