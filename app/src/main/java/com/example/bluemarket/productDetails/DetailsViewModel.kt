package com.example.bluemarket.productDetails

import androidx.lifecycle.MutableLiveData
import com.example.bluemarket.home.repository.ProductRepository
import com.example.bluemarket.model.Product
import com.example.bluemarket.productDetails.repository.DetailsRepository
import com.example.bluemarket.utils.MySingleObserver
import com.example.bluemarket.utils.MyViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailsViewModel(detailsRepository: DetailsRepository) : MyViewModel() {
    val productDetailLiveData = MutableLiveData<List<Product>>()

    init {
        progressbarLiveData.value = true
        detailsRepository.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                progressbarLiveData.value = false
            }
            .subscribe(object : MySingleObserver<List<Product>>(compositeDisposable) {
                override fun onSuccess(t: List<Product>) {
                    productDetailLiveData.value = t
                }
            })
    }
}