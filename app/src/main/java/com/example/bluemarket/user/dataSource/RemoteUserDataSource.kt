package com.example.bluemarket.user.dataSource

import com.example.bluemarket.model.Token

import com.example.bluemarket.retrofit.ApiService
import io.reactivex.Single

class RemoteUserDataSource(val apiService: ApiService) : UserDataSource {
    override fun login(username: String, password: String): Single<Token> = apiService.login(username, password)
}