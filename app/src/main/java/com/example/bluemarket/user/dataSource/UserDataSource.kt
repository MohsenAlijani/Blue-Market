package com.example.bluemarket.user.dataSource

import com.example.bluemarket.model.Token

import io.reactivex.Single

interface UserDataSource {
    fun login(username: String, password: String): Single<Token>
}