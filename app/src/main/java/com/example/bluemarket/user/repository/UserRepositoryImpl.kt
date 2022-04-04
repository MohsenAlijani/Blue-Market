package com.example.bluemarket.user.repository

import com.example.bluemarket.model.Token
import com.example.bluemarket.user.dataSource.UserDataSource
import io.reactivex.Single

class UserRepositoryImpl(val remoteUserDataSource: UserDataSource): UserRepository {
    override fun login(username: String, password: String): Single<Token> = remoteUserDataSource.login(username, password)

}