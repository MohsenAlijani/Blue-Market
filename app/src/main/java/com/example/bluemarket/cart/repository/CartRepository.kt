package com.example.bluemarket.cart.repository

import com.example.bluemarket.model.CartProduct
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface CartRepository {
    fun addProduct(cartProduct: CartProduct): Long
    fun deleteProduct(cartProduct: CartProduct): Int
    fun updateProduct(cartProduct: CartProduct): Int
    fun getProducts(): Flowable<List<CartProduct>>
}