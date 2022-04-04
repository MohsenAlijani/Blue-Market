package com.example.bluemarket.productDetails.dataSource

import com.example.bluemarket.model.Product
import io.reactivex.Single

interface DetailsDataSource {
    fun getProducts(): Single<List<Product>>
}