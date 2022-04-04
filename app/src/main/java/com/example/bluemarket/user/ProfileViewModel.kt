package com.example.bluemarket.user

import androidx.lifecycle.MutableLiveData
import com.example.bluemarket.model.Token
import com.example.bluemarket.user.repository.UserRepository
import com.example.bluemarket.utils.MySingleObserver
import com.example.bluemarket.utils.MyViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProfileViewModel(val userRepository: UserRepository) : MyViewModel() {

    val loginLiveData = MutableLiveData<Boolean>()

/* Unfortunately the api server login service is not responding...
 I will just show the signup and login procedure in app without connecting to server.*/

    fun login(username: String, password: String) {
        userRepository.login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : MySingleObserver<Token>(compositeDisposable) {
                override fun onSuccess(t: Token) {
                    loginLiveData.value = true
                }
            })
    }

}