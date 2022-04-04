package com.example.bluemarket.productDetails.repository

import com.example.bluemarket.model.Product
import com.example.bluemarket.productDetails.dataSource.RemoteDetailsDataSource
import io.reactivex.Single

class DetailsRepositoryImpl(val remoteDetailsDataSource: RemoteDetailsDataSource): DetailsRepository {
    override fun getProducts(): Single<List<Product>> = remoteDetailsDataSource.getProducts()
}