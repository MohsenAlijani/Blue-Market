package com.example.bluemarket.allProducts.repository

import com.example.bluemarket.allProducts.dataSource.AllProductsDataSource
import com.example.bluemarket.model.Product
import io.reactivex.Single

class AllProductsRepositoryImpl(val remoteAllProductsDataSource: AllProductsDataSource): AllProductsRepository {
    override fun getProducts(): Single<List<Product>> = remoteAllProductsDataSource.getProducts()
}