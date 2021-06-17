package com.mdgn.ecommerce.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.mdgn.ecommerce.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.itemButton.setOnClickListener {
            val action = HomeFragmentDirections.actionToItemToItemFragment()
            it.findNavController().navigate(action)
        }

        binding.userButton.setOnClickListener {
            val action = HomeFragmentDirections.actionToUserBased()
            it.findNavController().navigate(action)
        }



    }



}