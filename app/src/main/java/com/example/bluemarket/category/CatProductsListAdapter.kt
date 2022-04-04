package com.example.bluemarket.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bluemarket.R
import com.example.bluemarket.model.Product
import com.example.bluemarket.utils.ImageLoading

class CatProductsListAdapter(val productsList: List<Product>, val imageLoading: ImageLoading) :
    RecyclerView.Adapter<CatProductsListAdapter.CatProductsListViewHolder>() {

    var onProductItemClickListener: OnProductItemClickListener? = null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CatProductsListViewHolder {
        return CatProductsListViewHolder(
            LayoutInflater.from(p0.context).inflate(R.layout.show_all_products_item, p0, false)
        )
    }

    override fun onBindViewHolder(p0: CatProductsListViewHolder, p1: Int) {
        val product = productsList[p1]
        imageLoading.load(p0.image, product.image)
        p0.title.text = product.title
        p0.price.text = product.price

        p0.itemView.setOnClickListener {
            onProductItemClickListener?.onProductItemClicked(product)
        }
    }

    override fun getItemCount(): Int = productsList.size

    fun setProductItemListener(onProductItemClickListener: OnProductItemClickListener) {
        this.onProductItemClickListener = onProductItemClickListener
    }

    class CatProductsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.img_all_products)
        val title = itemView.findViewById<TextView>(R.id.txt_all_products_title)
        val price = itemView.findViewById<TextView>(R.id.txt_all_products_price)
    }

    interface OnProductItemClickListener {
        fun onProductItemClicked(product: Product)
    }
}

