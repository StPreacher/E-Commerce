package com.mdgn.ecommerce.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.google.firebase.storage.ktx.storage
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.databinding.FragmentProductPageBinding
import com.mdgn.ecommerce.util.getProgressDrawable
import com.mdgn.ecommerce.util.loadImage
import kotlinx.android.synthetic.main.fragment_product_page.view.*


class ProductPageFragment : Fragment() {

    private lateinit var binding : FragmentProductPageBinding

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

        binding.imageFromStorage.loadImage("https://firebasestorage.googleapis.com/v0/b/e-commerce-16007.appspot.com/o/TV%20Aksesuarlar%C4%B1%201.jfif?alt=media&token=8153f14e-43e6-4199-b03e-66c164be607b",
        getProgressDrawable(binding.imageFromStorage.context))

    }


}