package com.example.bluemarket.category.dataSource

import com.example.bluemarket.model.Product
import com.example.bluemarket.retrofit.ApiService
import io.reactivex.Single

class RemoteCatDataSource(val apiService: ApiService) : CatDataSource {
    override fun getCategories(): Single<List<String>> = apiService.getCategories()
    override fun getElectronics(): Single<List<Product>> = apiService.getElectronics()
    override fun getJeweleries(): Single<List<Product>> = apiService.getJeweleries()
    override fun getMenCloth(): Single<List<Product>> = apiService.getMenCloth()
    override fun getWomanCloth(): Single<List<Product>> = apiService.getWomenCloth()
}