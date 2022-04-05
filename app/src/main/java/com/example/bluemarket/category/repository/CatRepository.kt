package com.example.bluemarket.category.repository

import com.example.bluemarket.model.Product
import io.reactivex.Single

interface CatRepository {
    fun getCategories(): Single<List<String>>
    fun getElectronics(): Single<List<Product>>
    fun getJeweleries(): Single<List<Product>>
    fun getMenCloth(): Single<List<Product>>
    fun getWomanCloth(): Single<List<Product>>
}