package com.example.bluemarket.productDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.bluemarket.R
import com.example.bluemarket.cart.CartViewModel
import com.example.bluemarket.home.HomeViewModel
import com.example.bluemarket.model.CartProduct
import com.example.bluemarket.utils.ImageLoading
import com.example.bluemarket.utils.MyFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment : MyFragment() {

    //TODO: remove homeViewModel and CartViewModel and create DetailsViewModel and use it here
    val detailsViewModel: DetailsViewModel by viewModel()
    val imageLoading: ImageLoading by inject()
    val cartViewModel: CartViewModel by viewModel()
    val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val image = view.findViewById<ImageView>(R.id.img_detail)
        val title = view.findViewById<TextView>(R.id.txt_detail_title)
        val price = view.findViewById<TextView>(R.id.txt_detail_price)
        val desc = view.findViewById<TextView>(R.id.txt_detail_desc)
        val cat = view.findViewById<TextView>(R.id.txt_detail_cat)
        val close = view.findViewById<ImageView>(R.id.img_detail_close)
        val addToCart = view.findViewById<Button>(R.id.btn_detail_addToCart)

        detailsViewModel.progressbarLiveData.observe(viewLifecycleOwner) {
            showProgressBar(it)
        }

        val productID = args.productId
//        homeViewModel.productId = requireArguments().getInt("productId")

        detailsViewModel.productDetailLiveData.observe(viewLifecycleOwner) { productList ->
            val product = productList[productID - 1]
            imageLoading.load(image, product.image)
            title.text = product.title
            price.text = product.price
            desc.text = product.description
            cat.text = product.category

            val imgDecrease = view.findViewById<ImageView>(R.id.img_detail_decrease)
            val imgDelete = view.findViewById<ImageView>(R.id.img_detail_delete)
            val txtOrderedNum = view.findViewById<TextView>(R.id.txt_details_ordered_number)
            var cartProduct = CartProduct(
                product.id,
                product.category,
                product.description,
                product.image,
                product.price,
                product.title,
                quantity = 0
            )

            //TODO   What should i do with the cartList observation?
            cartViewModel.cartLiveData.observe(viewLifecycleOwner) { cartList ->
                cartList.forEach {
                    if (it.id == product.id) {
                        cartProduct = it
                    }
                }
                txtOrderedNum?.text = cartProduct.quantity.toString()

                addToCart.setOnClickListener {
                    if (cartProduct.quantity == 0) {
                        cartProduct.quantity++
                        cartViewModel.addProduct(cartProduct)
                    } else {
                        cartProduct.quantity++
                        cartViewModel.updateProduct(cartProduct)
                    }
                }

                imgDecrease.setOnClickListener {
                    cartProduct.quantity--
                    cartViewModel.updateProduct(cartProduct)
                    txtOrderedNum?.text = cartProduct.quantity.toString()
                }

                imgDelete.setOnClickListener {
                    cartProduct.quantity--
                    cartViewModel.deleteProduct(cartProduct)
                    txtOrderedNum?.text = cartProduct.quantity.toString()
                }

                if (cartProduct.quantity == 1) {
                    imgDecrease.visibility = View.INVISIBLE
                    imgDelete.visibility = View.VISIBLE
                } else if (cartProduct.quantity > 1) {
                    imgDecrease.visibility = View.VISIBLE
                    imgDelete.visibility = View.INVISIBLE
                } else {
                    imgDecrease.visibility = View.INVISIBLE
                    imgDelete.visibility = View.INVISIBLE
                }

                close.setOnClickListener {
                    activity?.onBackPressed()
                }
            }
        }
    }
}





