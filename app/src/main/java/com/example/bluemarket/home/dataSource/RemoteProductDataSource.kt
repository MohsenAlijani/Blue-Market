package com.example.bluemarket.home.dataSource

import com.example.bluemarket.model.Product
import com.example.bluemarket.retrofit.ApiService
import io.reactivex.Single

class RemoteProductDataSource(val apiService: ApiService) : ProductDataSource {
    override fun getProducts(): Single<List<Product>> = apiService.getProducts()
    override fun getLimitedProducts(productsNumber: Int): Single<List<Product>> = apiService.getLimitedProducts(productsNumber)
}