package com.mdgn.ecommerce.ui.category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.databinding.FragmentCategoryBinding
import com.mdgn.ecommerce.model.KategoriList
import com.mdgn.ecommerce.ui.vm.CategoryFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private val categoryAdapter = CategoryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCategoryBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel : CategoryFragmentViewModel by viewModels()
        viewmodel.getAllCategories("kategori")

        binding.categoryListView.apply {
            layoutManager =LinearLayoutManager(context)
            adapter = categoryAdapter
        }
        observeViewModel(viewmodel)

    }
    private fun observeViewModel(viewmodel: CategoryFragmentViewModel) {
        viewmodel.categoryList.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("Response", it.toList().toString())
                categoryAdapter.updateUserList(it)
                categoryAdapter.notifyDataSetChanged()
            }
        })

    }

}