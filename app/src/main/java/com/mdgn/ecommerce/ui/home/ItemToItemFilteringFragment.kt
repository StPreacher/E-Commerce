package com.mdgn.ecommerce.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.databinding.FragmentItemToItemFilteringBinding
import com.mdgn.ecommerce.ui.vm.ItemToItemFilteringViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemToItemFilteringFragment : Fragment() {

    private lateinit var binding : FragmentItemToItemFilteringBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemToItemFilteringBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel : ItemToItemFilteringViewModel by viewModels()
        viewmodel.getProductMatrixFromFirestore("rated_products")
    }


}