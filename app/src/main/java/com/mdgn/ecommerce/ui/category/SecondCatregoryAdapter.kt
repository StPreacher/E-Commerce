package com.mdgn.ecommerce.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.databinding.SecondRowCategoryBinding
import com.mdgn.ecommerce.model.AltKategori

class SecondCatregoryAdapter (var categoryList : List<AltKategori>) : RecyclerView.Adapter<SecondCatregoryAdapter.SecondViewHolder>() {


    inner class SecondViewHolder(val view : SecondRowCategoryBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<SecondRowCategoryBinding>(inflater, R.layout.second_row_category,parent,false)

        return SecondViewHolder(view)
    }

    override fun onBindViewHolder(holder: SecondViewHolder, position: Int) {
        holder.view.altKategori = categoryList[position]
    }

    override fun getItemCount(): Int = categoryList.size
}