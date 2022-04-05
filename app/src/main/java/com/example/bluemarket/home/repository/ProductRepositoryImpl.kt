package com.example.bluemarket.home.repository

import com.example.bluemarket.home.dataSource.ProductDataSource
import com.example.bluemarket.model.Product
import io.reactivex.Single

class ProductRepositoryImpl(val remoteProductDataSource: ProductDataSource) : ProductRepository {
    override fun getProducts(): Single<List<Product>> = remoteProductDataSource.getProducts()
    override fun getLimitedProducts(productsNumber: Int): Single<List<Product>> = remoteProductDataSource.getLimitedProducts(productsNumber)
}