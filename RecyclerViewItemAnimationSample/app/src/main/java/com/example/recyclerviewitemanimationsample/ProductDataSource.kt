package com.example.recyclerviewitemanimationsample

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductDataSource @Inject constructor() {

    private var productList: ArrayList<Product>? = null

    fun getProductList(): ArrayList<Product> {
        if (productList == null) {
            productList = getDefaultProductList()
        }
        return productList!!
    }

    private fun getDefaultProductList(): ArrayList<Product> {
        val productArrayList = ArrayList<Product>()
        productArrayList.add(Product("bike", R.drawable.ic_bike))
        productArrayList.add(Product("motorcycle", R.drawable.ic_motorcycle))
        productArrayList.add(Product("bag", R.drawable.ic_bag))
        productArrayList.add(Product("dress", R.drawable.ic_dress))
        productArrayList.add(Product("seat", R.drawable.ic_siege))
        productArrayList.add(Product("backpack", R.drawable.ic_backpack))
        productArrayList.add(Product("chair", R.drawable.ic_chair))
        productArrayList.add(Product("teddy", R.drawable.ic_teddy))
        productArrayList.add(Product("vase", R.drawable.ic_vase))
        productArrayList.add(Product("drum", R.drawable.ic_tambour))
        return productArrayList
    }
}
