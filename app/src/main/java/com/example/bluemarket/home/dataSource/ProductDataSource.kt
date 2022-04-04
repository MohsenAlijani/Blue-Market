package com.example.bluemarket.home.dataSource

import com.example.bluemarket.model.Product
import io.reactivex.Single

interface ProductDataSource {
    fun getProducts(): Single<List<Product>>
    fun getLimitedProducts(productsNumber: Int) : Single<List<Product>>
}