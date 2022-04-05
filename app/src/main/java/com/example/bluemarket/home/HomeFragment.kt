package com.example.bluemarket.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bluemarket.R
import com.example.bluemarket.category.CAT
import com.example.bluemarket.utils.ImageLoading
import com.example.bluemarket.utils.MyFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class HomeFragment : MyFragment(),
    HomeProductsAdapter.OnSeeAllProductsClickListener,
    HomeProductsAdapter.OnProductItemClickListener {

    val homeViewModel: HomeViewModel by viewModel()
    val imageLoading: ImageLoading by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.progressbarLiveData.observe(viewLifecycleOwner) {
            showProgressBar(it)
        }

        val imgBanner = view.findViewById<ImageView>(R.id.img_main_banner)
        homeViewModel.bannerLiveData.observe(viewLifecycleOwner) { product ->
            imageLoading.load(imgBanner, product.image)
            val animation = AlphaAnimation(0.5F, 1F)
            animation.duration = 1500
            imgBanner.startAnimation(animation)

            imgBanner.setOnClickListener {
                val action: NavDirections =
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(productId = product.id)
                findNavController().navigate(action)
            }

            val similarProducts = view.findViewById<TextView>(R.id.txt_home_similar_products)
            similarProducts?.setOnClickListener {
                CAT = product.category
                val action: NavDirections =
                    HomeFragmentDirections.actionHomeFragmentToCatProductsListFragment()
                findNavController().navigate(action)
            }
        }

        homeViewModel.productsLiveData.observe(viewLifecycleOwner) {
            val recyclerView = view.findViewById<RecyclerView>(R.id.rv_home_products)
            val adapter: HomeProductsAdapter by inject { parametersOf(it) }
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = adapter
            adapter.seeAllProducts(this)
            adapter.setProductListener(this)
        }

        val searchFrame = view.findViewById<FrameLayout>(R.id.edt_home_all_products_search)
        searchFrame.setOnClickListener {
            val action: NavDirections =
                HomeFragmentDirections.actionHomeFragmentToShowAllProductsFragment()
            findNavController().navigate(action)
        }
    }

    override fun onSeeAllProductsClick() {
        val action: NavDirections =
            HomeFragmentDirections.actionHomeFragmentToShowAllProductsFragment()
        findNavController().navigate(action)
    }

    override fun onProductItemClick(productId: Int) {
        val action: NavDirections =
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(productId = productId)
        findNavController().navigate(action)
    }
}



