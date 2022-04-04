package com.example.bluemarket.productDetails.repository

import com.example.bluemarket.model.Product
import io.reactivex.Single

interface DetailsRepository {
    fun getProducts(): Single<List<Product>>
}