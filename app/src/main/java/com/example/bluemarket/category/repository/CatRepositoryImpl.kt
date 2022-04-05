package com.example.bluemarket.category.repository

import com.example.bluemarket.category.dataSource.CatDataSource
import com.example.bluemarket.model.Product
import io.reactivex.Single

class CatRepositoryImpl(val remoteCatDataSource: CatDataSource) : CatRepository {
    override fun getCategories(): Single<List<String>> = remoteCatDataSource.getCategories()
    override fun getElectronics(): Single<List<Product>> = remoteCatDataSource.getElectronics()
    override fun getJeweleries(): Single<List<Product>> = remoteCatDataSource.getJeweleries()
    override fun getMenCloth(): Single<List<Product>> = remoteCatDataSource.getMenCloth()
    override fun getWomanCloth(): Single<List<Product>> = remoteCatDataSource.getWomanCloth()
}