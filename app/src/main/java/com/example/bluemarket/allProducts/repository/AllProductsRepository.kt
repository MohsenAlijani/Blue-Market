package com.example.bluemarket.allProducts.repository

import com.example.bluemarket.model.Product
import io.reactivex.Single

interface AllProductsRepository {
    fun getProducts(): Single<List<Product>>
}