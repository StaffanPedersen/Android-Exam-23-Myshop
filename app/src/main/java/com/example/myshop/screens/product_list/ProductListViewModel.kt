package com.example.myshop.screens.product_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myshop.data.MyshopRepository
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

    private val _filterWomensClothing = MutableStateFlow(false)
    val filterWomensClothing = _filterWomensClothing.asStateFlow()

    val product = combine(
        MyshopRepository.getProduct(),
        filterMensClothing,
        filterElectronics,
        filterWomensClothing
    ) { productList, mensClothing, electronics, womensClothing ->
        productList
            .filter { !mensClothing || it.category ==  "men's clothing" }
            .filter { !electronics || it.category == "electronics" }
            .filter { !womensClothing || it.category == "women's clothing" }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = emptyList()
    )

    fun setProductFilter(
        inSpaceOnly: Boolean? = null,
        activeOnly: Boolean? = null,
        favoritesOnly: Boolean? = null
    ) {
        inSpaceOnly?.let {
            _filterMensClothing.value = it
        }
        activeOnly?.let {
            _filterElectronics.value = it
        }
        favoritesOnly?.let {
            _filterWomensClothing.value = it
        }
    }
}