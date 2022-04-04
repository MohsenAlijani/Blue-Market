package com.example.bluemarket.home.repository

import com.example.bluemarket.model.Product
import io.reactivex.Single

interface ProductRepository {
    fun getProducts(): Single<List<Product>>
    fun getLimitedProducts(productsNumber: Int) : Single<List<Product>>
}