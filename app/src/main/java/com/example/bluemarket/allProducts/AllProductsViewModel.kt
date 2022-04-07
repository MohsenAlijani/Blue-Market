package com.example.bluemarket.allProducts

import androidx.lifecycle.MutableLiveData
import com.example.bluemarket.allProducts.repository.AllProductsRepository
import com.example.bluemarket.model.Product
import com.example.bluemarket.productDetails.repository.DetailsRepository
import com.example.bluemarket.utils.MySingleObserver
import com.example.bluemarket.utils.MyViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AllProductsViewModel(allProductsRepository: AllProductsRepository) : MyViewModel() {

    val allProductsLiveData = MutableLiveData<List<Product>>()

    init {
        progressbarLiveData.value = true
        allProductsRepository.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                progressbarLiveData.value = false
            }
            .subscribe(object : MySingleObserver<List<Product>>(compositeDisposable) {
                override fun onSuccess(t: List<Product>) {
                    allProductsLiveData.value = t
                }
            })
    }
}