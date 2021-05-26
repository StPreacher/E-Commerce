package com.mdgn.ecommerce.ui.category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mdgn.ecommerce.R


class ThirdCategoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val altCategoryID = arguments?.let {
            ThirdCategoryFragmentArgs.fromBundle(it).altCategoryID.toString()
        }

        val categoryID = arguments?.let {
            ThirdCategoryFragmentArgs.fromBundle(it).categoryID.toString()
        }

        Log.v("ThirdCAtegoryFragment","kategori = $categoryID - altkategori = $altCategoryID")

    }

}