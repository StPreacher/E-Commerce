package com.mdgn.ecommerce.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mdgn.ecommerce.databinding.FragmentProductPageBinding
import com.mdgn.ecommerce.ui.category.adapter.ProductAdapter
import com.mdgn.ecommerce.ui.vm.ProductDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductPageFragment : Fragment() {

    private var altCategoryID : Int? = null
    private var categoryID : Int? = null
    private var altAltCategoryID : Int? = null
    private lateinit var binding : FragmentProductPageBinding
    private val productAdapter = ProductAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProductPageBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            categoryID = ProductPageFragmentArgs.fromBundle(it).categoryID
            altCategoryID = ProductPageFragmentArgs.fromBundle(it).altCategoryID
            altAltCategoryID = ProductPageFragmentArgs.fromBundle(it).altAltCategoryID
        }
            altAltCategoryID = altAltCategoryID?.minus(1)

        println(categoryID)
        println(altCategoryID)
        println(altAltCategoryID)

        val viewModel : ProductDetailViewModel by viewModels()
        viewModel.getProductDetails(
            "kategori",
            "alt_kategori",
            "alt_alt_kategori",
            "detail",
            categoryID!!,
            altCategoryID!!,
            altAltCategoryID!!
        )

        binding.rvProduct.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(context)
        }
        observeViewModel(viewModel)

    }

    private fun observeViewModel(viewModel: ProductDetailViewModel) {
        viewModel.productList.observe(viewLifecycleOwner, Observer {
            it?.let {
                productAdapter.apply {
                    productDetailList = it
                    notifyDataSetChanged()
                }
            }
        })
    }


}