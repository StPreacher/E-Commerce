package com.mdgn.ecommerce.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.databinding.SecondRowCategoryBinding
import com.mdgn.ecommerce.model.AltKategori
import kotlinx.android.synthetic.main.second_row_category.view.*

class SecondCatregoryAdapter (var categoryList : List<AltKategori>,var categoryID : Int?)
    : RecyclerView.Adapter<SecondCatregoryAdapter.SecondViewHolder>(), CategoryClickListener {


    inner class SecondViewHolder(val view : SecondRowCategoryBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<SecondRowCategoryBinding>(inflater, R.layout.second_row_category,parent,false)

        return SecondViewHolder(view)
    }

    override fun onBindViewHolder(holder: SecondViewHolder, position: Int) {
        holder.view.altKategori = categoryList[position]
        holder.view.altcategoryID.text = (holder.adapterPosition+1).toString()
        holder.view.listener = this

    }

    override fun getItemCount(): Int = categoryList.size

    override fun onCategoryClicked(v: View) {

        val altCategoryID = v.altcategoryID.text.toString().toInt()
        val action = SecondCategoryFragmentDirections.actionSecondCategoryFragmentToThirdFragment(altCategoryID,categoryID!!)
        Navigation.findNavController(v).navigate(action)

    }
}