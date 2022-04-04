package com.example.bluemarket.cart.repository

import com.example.bluemarket.cart.room.AppDatabase
import com.example.bluemarket.model.CartProduct
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class CartRepositoryImpl(val db: AppDatabase):CartRepository {
    override fun addProduct(cartProduct: CartProduct): Long {
        return db.getCartDao().addProduct(cartProduct)
    }

    override fun deleteProduct(cartProduct: CartProduct): Int {
        return db.getCartDao().deleteProduct(cartProduct)
    }

    override fun updateProduct(cartProduct: CartProduct): Int {
        return db.getCartDao().updateProduct(cartProduct)
    }

    override fun getProducts(): Flowable<List<CartProduct>> {
        return db.getCartDao().getProducts()
    }
}