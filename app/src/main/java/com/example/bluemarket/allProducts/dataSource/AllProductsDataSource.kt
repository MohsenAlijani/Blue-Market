package com.example.bluemarket.allProducts.dataSource

import com.example.bluemarket.model.Product
import io.reactivex.Single

interface AllProductsDataSource {
    fun getProducts(): Single<List<Product>>
}