package com.example.bluemarket.allProducts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bluemarket.R
import com.example.bluemarket.home.HomeViewModel
import com.example.bluemarket.utils.MyFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class ShowAllProductsFragment : MyFragment(),
    ShowAllProductsAdapter.OnProductListItemClickListener {

    val allProductsViewModel: AllProductsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allProductsViewModel.progressbarLiveData.observe(viewLifecycleOwner) {
            showProgressBar(it)
        }

        val close = view.findViewById<ImageView>(R.id.img_all_products_close)
        close.setOnClickListener {
            activity?.onBackPressed()
        }

        allProductsViewModel.allProductsLiveData.observe(viewLifecycleOwner) {
            val adapter: ShowAllProductsAdapter by inject{ parametersOf(it) }
            val recyclerView = view.findViewById<RecyclerView>(R.id.rv_all_products)
            recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
            adapter.setListProductListener(this)

            val search = view.findViewById<SearchView>(R.id.btn_all_products_search)
            search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.getFilter().filter(newText)
                    return false
                }
            })
        }
    }

    override fun onItemClick(productId: Int) {
        val action: NavDirections =
            ShowAllProductsFragmentDirections.actionShowAllProductsFragmentToDetailsFragment(productId = productId)
        findNavController().navigate(action)
    }
}