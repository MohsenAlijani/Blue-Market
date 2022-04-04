package com.example.bluemarket.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bluemarket.R
import com.example.bluemarket.home.HomeFragmentDirections
import com.example.bluemarket.utils.MyFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CatFragment : MyFragment(), CatAdapter.OnCatItemClickListener {
    val catViewModel: CatViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        catViewModel.progressbarLiveData.observe(viewLifecycleOwner) {
            showProgressBar(it)
        }

        catViewModel.catLiveData.observe(viewLifecycleOwner) {
            val recyclerView = view.findViewById<RecyclerView>(R.id.rv_cats)
            val adapter: CatAdapter by inject { parametersOf(it) }
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = adapter
            adapter.setCatListener(this)
        }
    }

    override fun onCatItemClick(catName: String) {
        CAT = catName
        val action: NavDirections = CatFragmentDirections.actionCatFragmentToCatProductsListFragment22()
        findNavController().navigate(action)
    }
}