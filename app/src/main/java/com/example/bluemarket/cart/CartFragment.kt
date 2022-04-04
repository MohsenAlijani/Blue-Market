package com.example.bluemarket.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.NestedScrollView
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bluemarket.R
import com.example.bluemarket.model.CartProduct
import com.example.bluemarket.utils.MyFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class CartFragment : MyFragment(), CartAdapter.CartProductItemControl {

    val cartViewModel: CartViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartViewModel.cartLiveData.observe(viewLifecycleOwner) {
            val cartEmptyFrame = view.findViewById<ConstraintLayout>(R.id.cart_empty_frame)
            val cartList = view.findViewById<NestedScrollView>(R.id.cart_list_constraint)

            if (it.isNotEmpty()) {
                cartEmptyFrame.visibility = View.INVISIBLE
                cartList.visibility = View.VISIBLE
            } else {
                cartEmptyFrame.visibility = View.VISIBLE
                cartList.visibility = View.INVISIBLE
            }

            val recyclerView = view.findViewById<RecyclerView>(R.id.rv_cart)
            val adapter: CartAdapter by inject { parametersOf(it) }
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
            adapter.setCartProductItem(this)

            val txtTotalPrice = view.findViewById<TextView>(R.id.txt_cart_final_total_price)
            txtTotalPrice?.text = String.format("%.2f", (cartViewModel.getShoppingCartPrice()))

            val txtTotalQuantity = view.findViewById<TextView>(R.id.txt_cart_final_total_quantity)
            txtTotalQuantity?.text = cartViewModel.getShoppingCartSize().toString()
        }


    }


//    override fun cartTotalQuantityAndPriceControl() {
//
//    }

    override fun onProductItemClick(productId: Int) {
        val action: NavDirections =
            CartFragmentDirections.actionCartToDetailsFragment(productId = productId)
        findNavController().navigate(action)
    }


    override fun onProductItemPlusClicked(cartItem: CartProduct) {
        cartItem.quantity += 1
        cartViewModel.updateProduct(cartItem)

    }

    override fun onProductItemMinusClicked(cartItem: CartProduct) {
        cartItem.quantity -= 1
        cartViewModel.updateProduct(cartItem)
    }

    override fun onProductItemRemoveClicked(cartItem: CartProduct) {
        cartViewModel.deleteProduct(cartItem)
    }


//    override fun onResume() {
//        super.onResume()
//        if (REFRESH_CART) {
//            NavHostFragment.findNavController(this).navigate(R.id.cartFragment)
//        }
//        REFRESH_CART = false
//    }
}