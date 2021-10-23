package com.example.recyclerviewitemanimationsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    dataSource: ProductDataSource
) : ViewModel() {

    private val _productList = MutableLiveData<List<Product>>(dataSource.getProductList())
    val productList: LiveData<List<Product>>
        get() = _productList

    fun toggleLikeAtPosition(position: Int) {
        val currentList = _productList.value!!.toMutableList()
        val wasPreviouslyLiked = currentList[position].isLiked
        currentList[position] = currentList[position].copy(isLiked = !wasPreviouslyLiked)
        _productList.value = currentList
    }
}
