package com.example.bluemarket.user.repository

import com.example.bluemarket.model.Token
import io.reactivex.Single

interface UserRepository {
    fun login(username: String, password: String): Single<Token>
}