package com.example.bluemarket.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bluemarket.R
import com.example.bluemarket.model.Product
import com.example.bluemarket.utils.MyFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

var CAT= ""
class CatProductsListFragment : MyFragment(), CatProductsListAdapter.OnProductItemClickListener {


    val catViewModel: CatViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cat_products_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        catViewModel.progressbarLiveData.observe(viewLifecycleOwner) {
            showProgressBar(it)
        }

        //TODO: why is this commented? never mind, use the args instead
//        CAT = requireArguments().getString("cat").toString()

        catViewModel.catProductsLiveData.observe(viewLifecycleOwner) {
            val txtHeader = view.findViewById<TextView>(R.id.txt_cat_products_list_header)
            txtHeader.text = CAT
            val recyclerView = view.findViewById<RecyclerView>(R.id.rv_cat_products_list)
            val adapter : CatProductsListAdapter by inject { parametersOf(it) }
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
            adapter.setProductItemListener(this)
        }

        val close = view.findViewById<ImageView>(R.id.img_cat_product_list_close)
        close.setOnClickListener {
            activity?.onBackPressed()
        }
    }

    override fun onProductItemClicked(product: Product) {
        val action: NavDirections =
            CatProductsListFragmentDirections.actionCatProductsListFragmentToDetailsFragment(productId = product.id)
        findNavController().navigate(action)
    }
}