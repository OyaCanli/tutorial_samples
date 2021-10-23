package com.example.recyclerviewitemanimationsample

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    dataSource: ProductDataSource
) : ViewModel() {

    val productList = dataSource.getProductList()
}
