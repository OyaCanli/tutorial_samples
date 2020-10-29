package com.example.recyclerviewitemanimationsample

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class ProductViewModel @ViewModelInject constructor(
    dataSource : ProductDataSource,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val productList = dataSource.getProductList()
}