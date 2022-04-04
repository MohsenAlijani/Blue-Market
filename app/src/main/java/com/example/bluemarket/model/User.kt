package com.example.bluemarket.model

data class User(
    val address: UserAddress,
    val email: String,
    val id: Int,
    val name: UserName,
    val password: String,
    val phone: String,
    val username: String
)