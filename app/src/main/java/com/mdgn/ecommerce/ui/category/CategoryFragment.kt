package com.mdgn.ecommerce.ui.category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.ui.vm.CategoryFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel : CategoryFragmentViewModel by viewModels()
        viewmodel.getAllCategories("kategori")

        observeViewModel(viewmodel)


    }

    private fun observeViewModel(viewmodel: CategoryFragmentViewModel) {
        viewmodel.categoryList.observe(viewLifecycleOwner, Observer {
            Log.d("LastResponse", it?.kategoriList?.toList().toString())
        })

    }


}