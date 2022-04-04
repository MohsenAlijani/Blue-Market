package com.example.bluemarket.utils

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.bluemarket.cart.CartAdapter
import com.example.bluemarket.cart.CartViewModel
import com.example.bluemarket.cart.repository.CartRepository
import com.example.bluemarket.cart.repository.CartRepositoryImpl
import com.example.bluemarket.cart.room.AppDatabase.Companion.getAppDatabase
import com.example.bluemarket.category.CatAdapter
import com.example.bluemarket.category.CatProductsListAdapter
import com.example.bluemarket.category.CatViewModel
import com.example.bluemarket.category.dataSource.RemoteCatDataSource
import com.example.bluemarket.category.repository.CatRepository
import com.example.bluemarket.category.repository.CatRepositoryImpl
import com.example.bluemarket.home.HomeProductsAdapter
import com.example.bluemarket.home.HomeViewModel
import com.example.bluemarket.home.ShowAllProductsAdapter
import org.koin.androidx.viewmodel.dsl.viewModel
import com.example.bluemarket.home.dataSource.RemoteProductDataSource
import com.example.bluemarket.home.repository.ProductRepository
import com.example.bluemarket.home.repository.ProductRepositoryImpl
import com.example.bluemarket.model.CartProduct
import com.example.bluemarket.model.Product
import com.example.bluemarket.productDetails.DetailsViewModel
import com.example.bluemarket.retrofit.getClient
import com.example.bluemarket.user.ProfileViewModel
import com.example.bluemarket.user.dataSource.RemoteUserDataSource
import com.example.bluemarket.user.repository.UserRepository
import com.example.bluemarket.user.repository.UserRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module


class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val myModules = module {
            single<ImageLoading> { ImageLoadingImpl() }
            single { getClient() }
            factory<ProductRepository> { ProductRepositoryImpl(RemoteProductDataSource(get())) }
            viewModel { HomeViewModel(get()) }
            factory { (productsList: List<Product>) -> HomeProductsAdapter(get(), get()) }
            factory<CatRepository> { CatRepositoryImpl(RemoteCatDataSource(get())) }
            viewModel { CatViewModel(get()) }
            factory { (CatsList: List<String>) -> CatAdapter(get()) }
            factory { (allProductsList: ArrayList<Product>) -> ShowAllProductsAdapter(get(), get()) }
            factory { (productsList: List<Product>) -> CatProductsListAdapter(get(), get()) }
            factory<UserRepository> { UserRepositoryImpl(RemoteUserDataSource(get())) }
            viewModel { ProfileViewModel(get()) }
            factory { (cart: List<CartProduct>) -> CartAdapter(get(), get()) }
            single { getAppDatabase(applicationContext) }
            factory<CartRepository> { CartRepositoryImpl(get()) }
            viewModel { CartViewModel(get()) }
            viewModel { DetailsViewModel(get()) }

        }

        startKoin {
            androidContext(this@App)
            modules(myModules)
        }

    }
}