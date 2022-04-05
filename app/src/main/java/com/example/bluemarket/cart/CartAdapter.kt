package com.example.bluemarket.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.bluemarket.model.CartProduct
import com.example.bluemarket.utils.ImageLoading
import com.example.bluemarket.R


class CartAdapter(var cart: List<CartProduct>, val imageLoading: ImageLoading) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    var cartProductItemControl: CartProductItemControl? = null
    var productTotalPrice: Float = 0f

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CartViewHolder {
        return CartViewHolder(
            LayoutInflater.from(p0.context).inflate(R.layout.cart_products_item, p0, false)
        )
    }

    override fun onBindViewHolder(p0: CartViewHolder, p1: Int) {
        val product = cart[p1]
        productTotalPrice = (product.price.toFloat()) * (cart[p1].quantity)
        product.image.let { imageLoading.load(p0.image, it) }
        p0.txtTitle.text = product.title
        p0.txtPrice.text = product.price
        p0.txtNumber.text = cart[p1].quantity.toString()
        p0.txtTotalPrice.text = String.format("%.2f", productTotalPrice)

        p0.itemView.setOnClickListener {
            cartProductItemControl?.onProductItemClick(product.id)
        }
        p0.imgPlus.setOnClickListener {
            cartProductItemControl?.onProductItemPlusClicked(cart[p1])
        }

        p0.imgMinus.setOnClickListener {
            cartProductItemControl?.onProductItemMinusClicked(cart[p1])
        }

        p0.imgRemove.setOnClickListener {
            cartProductItemControl?.onProductItemRemoveClicked(cart[p1])
        }

        if (cart[p1].quantity > 1) {
            p0.imgMinus.visibility = View.VISIBLE
            p0.imgRemove.visibility = View.INVISIBLE
        } else if (cart[p1].quantity == 1) {
            p0.imgMinus.visibility = View.INVISIBLE
            p0.imgRemove.visibility = View.VISIBLE
        } else {
            cartProductItemControl?.onProductItemRemoveClicked(cart[p1])
        }
    }

    override fun getItemCount(): Int = cart.size

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.img_cart_product)
        val txtTitle = itemView.findViewById<TextView>(R.id.txt_cart_title)
        val txtPrice = itemView.findViewById<TextView>(R.id.txt_cart_price)
        val txtNumber = itemView.findViewById<TextView>(R.id.txt_cart_number)
        val txtTotalPrice = itemView.findViewById<TextView>(R.id.txt_cart_item_total_price)
        val imgPlus = itemView.findViewById<ImageView>(R.id.img_cart_product_plus)
        val imgMinus = itemView.findViewById<ImageView>(R.id.img_cart_product_minus)
        val imgRemove = itemView.findViewById<ImageView>(R.id.img_cart_product_remove)
    }

    fun setCartProductItem(cartProductItemControl: CartProductItemControl) {
        this.cartProductItemControl = cartProductItemControl
    }

    interface CartProductItemControl {
        fun onProductItemClick(productId: Int)
        fun onProductItemPlusClicked(cartItem: CartProduct)
        fun onProductItemMinusClicked(cartItem: CartProduct)
        fun onProductItemRemoveClicked(cartItem: CartProduct)
    }
}
