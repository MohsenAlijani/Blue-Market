package com.example.bluemarket.cart

import android.annotation.SuppressLint
import android.util.Log
import com.example.bluemarket.cart.repository.CartRepository
import com.example.bluemarket.model.CartProduct
import com.example.bluemarket.utils.MyViewModel
import androidx.lifecycle.MutableLiveData
import com.example.bluemarket.utils.MySingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import java.util.*

@SuppressLint("CheckResult")
class CartViewModel(val cartRepository: CartRepository): MyViewModel() {

    var cart : List<CartProduct>? = null
    var cartProduct: CartProduct? = null
    var cartLiveData = MutableLiveData<List<CartProduct>>()

    init {
        cartRepository.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                cartLiveData.value = it
                cart = it
            }, {
                Log.i("error", ":${it.toString()} ")
            })
    }

    fun addProduct(cartProduct: CartProduct) {
        cartRepository.addProduct(cartProduct)
    }

    fun deleteProduct(cartProduct: CartProduct) {
        cartRepository.deleteProduct(cartProduct)
    }

    fun updateProduct(cartProduct: CartProduct) {
        cartRepository.updateProduct(cartProduct)
    }

    fun getShoppingCartSize(): Int {
        var totalCartQuantity = 0
        cartLiveData.value?.forEach {
            totalCartQuantity += it.quantity
        }
        return totalCartQuantity
    }

    fun getShoppingCartPrice(): Float {
        var totalCartPrice = 0f
        cartLiveData.value?.forEach {
            totalCartPrice += (it.price.toFloat() * it.quantity)
        }
        return totalCartPrice
    }

    fun getSpecificProductQuantity(productId: Int): Int {
        var productQuantity = 0
        cart?.forEach {
            if (it.id == productId) {
                productQuantity = it.quantity
            }
        }
//        cartProductQuantityLiveData.value = productQuantity
        return productQuantity
    }
}