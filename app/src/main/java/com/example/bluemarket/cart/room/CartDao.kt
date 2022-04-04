package com.example.bluemarket.cart.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bluemarket.model.CartProduct
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.*

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(cartProduct: CartProduct): Long

    @Delete
    fun deleteProduct(cartProduct: CartProduct): Int

    @Update
    fun updateProduct(cartProduct: CartProduct): Int

    @Query("SELECT * FROM tbl_cart ")
    fun getProducts(): Flowable<List<CartProduct>>
}