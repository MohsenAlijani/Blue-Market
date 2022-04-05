package com.example.bluemarket.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bluemarket.R
import com.example.bluemarket.model.Product
import com.example.bluemarket.utils.ImageLoading

const val PRODUCT_ITEM_FIRST_IMAGE = 1
const val PRODUCT_ITEM = 2

class HomeProductsAdapter(val productList: List<Product>, val imageLoading: ImageLoading) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onSeeAllProductsClickListener: OnSeeAllProductsClickListener? = null
    var onProductItemClickListener: OnProductItemClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view: View
        return if (p1 == PRODUCT_ITEM_FIRST_IMAGE) {
            view =
                LayoutInflater.from(p0.context).inflate(R.layout.product_home_first_item, p0, false)
            HomeProductsFirstImageViewHolder(view)
        } else {
            view = LayoutInflater.from(p0.context).inflate(R.layout.product_home_item, p0, false)
            HomeProductsViewHolder(view)
        }
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val product = productList[p1]
        if (getItemViewType(p1) == PRODUCT_ITEM) {
            imageLoading.load((p0 as HomeProductsViewHolder).image, product.image)
            p0.txtTitle.text = product.title
            p0.txtPrice.text = product.price
            p0.itemView.setOnClickListener {
                onProductItemClickListener?.onProductItemClick(product.id)
            }
        } else {
            (p0 as HomeProductsFirstImageViewHolder).txtAllProducts.setOnClickListener {
                onSeeAllProductsClickListener?.onSeeAllProductsClick()
            }
        }
    }

    fun seeAllProducts(onSeeAllProductsClickListener: OnSeeAllProductsClickListener) {
        this.onSeeAllProductsClickListener = onSeeAllProductsClickListener
    }

    fun setProductListener(onProductItemClickListener: OnProductItemClickListener) {
        this.onProductItemClickListener = onProductItemClickListener
    }

    override fun getItemCount(): Int = productList.size

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            PRODUCT_ITEM_FIRST_IMAGE
        } else {
            PRODUCT_ITEM
        }
    }

    class HomeProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.img_home_productList)
        val txtTitle = itemView.findViewById<TextView>(R.id.txt_home_productList_title)
        val txtPrice = itemView.findViewById<TextView>(R.id.txt_home_productList_price)
    }

    class HomeProductsFirstImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtAllProducts = itemView.findViewById<TextView>(R.id.txt_home_seeAll)
    }

    interface OnSeeAllProductsClickListener {
        fun onSeeAllProductsClick()
    }

    interface OnProductItemClickListener {
        fun onProductItemClick(productId: Int)
    }
}