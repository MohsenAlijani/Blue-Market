package com.example.bluemarket.category

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.bluemarket.category.repository.CatRepository
import com.example.bluemarket.model.Product
import com.example.bluemarket.utils.MySingleObserver
import com.example.bluemarket.utils.MyViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CatViewModel(val catRepository: CatRepository) : MyViewModel() {

    val catLiveData = MutableLiveData<List<String>>()
    val catProductsLiveData = MutableLiveData<List<Product>>()

    init {
        progressbarLiveData.value = true
        catRepository.getCategories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                progressbarLiveData.value = false
            }
            .subscribe(object : MySingleObserver<List<String>>(compositeDisposable) {
                override fun onSuccess(t: List<String>) {
                    catLiveData.value = t
                }
            })

        progressbarLiveData.value = true
        switchCats()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                progressbarLiveData.value = false
            }
            .subscribe(object : MySingleObserver<List<Product>>(compositeDisposable) {
                override fun onSuccess(t: List<Product>) {
                    catProductsLiveData.value = t
                }
            })
    }

    private fun switchCats(): Single<List<Product>> {
        Log.i("LOG", "switchCats: $CAT")
        return when (CAT) {
            "electronics" -> {
                catRepository.getElectronics()
            }
            "jewelery" -> {
                catRepository.getJeweleries()
            }
            "men's clothing" -> {
                catRepository.getMenCloth()
            }
            else -> {
                catRepository.getWomanCloth()
            }
        }
    }
}
