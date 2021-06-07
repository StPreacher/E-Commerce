package com.mdgn.ecommerce.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mdgn.ecommerce.databinding.FragmentThirdBinding
import com.mdgn.ecommerce.ui.category.adapter.ThirdCategoryAdapter
import com.mdgn.ecommerce.ui.vm.ThirdCategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ThirdCategoryFragment : Fragment() {

    private var altCategoryID : Int? = null
    private var categoryID : Int? = null
    private lateinit var binding: FragmentThirdBinding
    private val categoryAdapter = ThirdCategoryAdapter(arrayListOf(),null,null)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentThirdBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            altCategoryID = ThirdCategoryFragmentArgs.fromBundle(it).altCategoryID
        }
        altCategoryID = altCategoryID?.minus(1)

        arguments?.let {
            categoryID = ThirdCategoryFragmentArgs.fromBundle(it).categoryID
        }

        val viewModel : ThirdCategoryViewModel by viewModels()
        viewModel.getThirdCategories("kategori",
            "alt_kategori",
            "alt_alt_kategori",
            categoryID!!,
            altCategoryID!!)

        binding.altAltCategoryRV.apply {
            adapter = categoryAdapter
            layoutManager = LinearLayoutManager(context)
        }

        observerViewModel(viewModel)

    }

    private fun observerViewModel(viewModel: ThirdCategoryViewModel) {
        viewModel.categoryList.observe(viewLifecycleOwner, Observer {
            categoryAdapter.categoryList = it
            categoryAdapter.categoryID = categoryID
            categoryAdapter.altCategoryID = altCategoryID
            categoryAdapter.notifyDataSetChanged()
        })
    }

}