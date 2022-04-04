package com.example.bluemarket.retrofit

import com.example.bluemarket.model.Product
import com.example.bluemarket.model.Token
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @GET("products")
    fun getProducts(): Single<List<Product>>

    @GET("products")
    fun getLimitedProducts(@Query("limit") productsNumber: Int): Single<List<Product>>

    @GET("products/categories")
    fun getCategories(): Single<List<String>>

    @GET("products/category/electronics")
    fun getElectronics() : Single<List<Product>>

    @GET("products/category/jewelery")
    fun getJeweleries() : Single<List<Product>>

    @GET("products/category/men's clothing")
    fun getMenCloth() : Single<List<Product>>

    @GET("products/category/women's clothing")
    fun getWomenCloth() : Single<List<Product>>

    @POST("https://fakestoreapi.com/auth/login")
    fun login(@Field("username") username: String, @Field("password") password:String): Single<Token>

//    @POST("https://fakestoreapi.com/users")
//    fun register(@Field("username") username:String, @Field("password") password:String), : Single<Token>
}

fun getClient(): ApiService {

    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

    val retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    return retrofit.create(ApiService::class.java)
}