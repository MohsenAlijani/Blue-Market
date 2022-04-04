package com.example.bluemarket.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "tbl_cart")
data class CartProduct(
    @PrimaryKey
    val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: String,
    val title: String,
    var quantity: Int
)