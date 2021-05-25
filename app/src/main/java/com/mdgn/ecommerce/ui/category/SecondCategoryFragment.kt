package com.mdgn.ecommerce.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mdgn.ecommerce.databinding.FragmentSecondCategoryBinding
import com.mdgn.ecommerce.ui.vm.SecondCategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SecondCategoryFragment : Fragment() {

    private var categoryId : Int ?= null
    private lateinit var binding: FragmentSecondCategoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSecondCategoryBinding.inflate(layoutInflater,container,false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            categoryId = SecondCategoryFragmentArgs.fromBundle(it).categoryID
        }

        val viewmodel : SecondCategoryViewModel by viewModels()
        viewmodel.getSecondCategories("kategori",categoryId!!)

        observeViewModel(viewmodel)
    }

    private fun observeViewModel(viewmodel: SecondCategoryViewModel) {
        viewmodel.categoryList.observe(viewLifecycleOwner, Observer {
            it?.let {
                println("alt kategori = ${it.toList()}")
            }
        })
    }


}