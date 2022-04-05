package com.example.bluemarket.productDetails.dataSource

import com.example.bluemarket.model.Product
import com.example.bluemarket.retrofit.ApiService
import io.reactivex.Single

class RemoteDetailsDataSource(val apiService: ApiService) : DetailsDataSource {
    override fun getProducts(): Single<List<Product>> = apiService.getProducts()
}