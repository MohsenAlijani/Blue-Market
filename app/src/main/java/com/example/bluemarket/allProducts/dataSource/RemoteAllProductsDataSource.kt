package com.example.bluemarket.allProducts.dataSource

import com.example.bluemarket.model.Product
import com.example.bluemarket.retrofit.ApiService
import io.reactivex.Single

class RemoteAllProductsDataSource(val apiService: ApiService): AllProductsDataSource {
    override fun getProducts(): Single<List<Product>> = apiService.getProducts()
}