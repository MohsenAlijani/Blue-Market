package com.example.bluemarket.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bluemarket.R
import com.example.bluemarket.model.Product
import com.example.bluemarket.utils.ImageLoading
import java.util.*
import kotlin.collections.ArrayList

class ShowAllProductsAdapter(val productsList: ArrayList<Product>, val imageLoading: ImageLoading) :
    RecyclerView.Adapter<ShowAllProductsAdapter.ShowAllProductsViewHolder>() {

    var productsFilterList = ArrayList<Product>()
    var onProductListItemClickListener: OnProductListItemClickListener? = null

    init {
        productsFilterList = productsList
    }

    fun getFilter(): Filter {
        return object : Filter() {
            val filterResults = FilterResults()
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()
                if (charSearch.isEmpty()) {
                    productsFilterList = productsList
                } else {
                    val resultList = ArrayList<Product>()
                    for (row in productsList) {
                        if (row.title.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    productsFilterList = resultList
                }

                filterResults.values = productsFilterList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                productsFilterList = filterResults.values as ArrayList<Product>
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ShowAllProductsViewHolder {
        return ShowAllProductsViewHolder(
            LayoutInflater.from(p0.context).inflate(R.layout.show_all_products_item, p0, false)
        )
    }

    override fun onBindViewHolder(p0: ShowAllProductsViewHolder, p1: Int) {
        val product = productsFilterList[p1]
        imageLoading.load(p0.image, product.image)
        p0.title.text = product.title
        p0.price.text = product.price

        p0.itemView.setOnClickListener {
            onProductListItemClickListener?.onItemClick(product.id)
        }
    }

    fun setListProductListener(onProductListItemClickListener: OnProductListItemClickListener) {
        this.onProductListItemClickListener = onProductListItemClickListener
    }

    override fun getItemCount(): Int = productsFilterList.size


    class ShowAllProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.img_all_products)
        val title = itemView.findViewById<TextView>(R.id.txt_all_products_title)
        val price = itemView.findViewById<TextView>(R.id.txt_all_products_price)
    }

    interface OnProductListItemClickListener {
        fun onItemClick(productId: Int)
    }
}
