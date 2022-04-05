package com.example.bluemarket.home

import androidx.lifecycle.MutableLiveData
import com.example.bluemarket.home.repository.ProductRepository
import com.example.bluemarket.model.Product
import com.example.bluemarket.utils.MySingleObserver
import com.example.bluemarket.utils.MyViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class HomeViewModel(productRepository: ProductRepository) : MyViewModel() {
    val bannerLiveData = MutableLiveData<Product>()
    val productsLiveData = MutableLiveData<List<Product>>()

    init {
        Observable.interval(4, TimeUnit.SECONDS)
            .flatMap {
                return@flatMap productRepository.getProducts()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .toObservable()
            }.subscribeBy(onNext = { productList ->
                val randomValue = Random.nextInt(1, 20)
                bannerLiveData.value = productList[randomValue]
            }, onError = {

            }).addTo(compositeDisposable)


        progressbarLiveData.value = true
        productRepository.getLimitedProducts(10)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                progressbarLiveData.value = false
            }
            .subscribe(object : MySingleObserver<List<Product>>(compositeDisposable) {
                override fun onSuccess(t: List<Product>) {
                    productsLiveData.value = t
                }
            })
    }
}




