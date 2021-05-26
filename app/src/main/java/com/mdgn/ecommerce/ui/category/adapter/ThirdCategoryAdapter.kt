package com.mdgn.ecommerce.ui.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.databinding.ThirdRowCategoryBinding
import com.mdgn.ecommerce.model.Kategori
import com.mdgn.ecommerce.model.ProductList

class ThirdCategoryAdapter (var categoryList : List<ProductList>) : RecyclerView.Adapter<ThirdCategoryAdapter.ThirdViewHolder>() {


    inner class ThirdViewHolder (val view : ThirdRowCategoryBinding) : RecyclerView.ViewHolder(view.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThirdViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ThirdRowCategoryBinding>(inflater, R.layout.third_row_category,parent,false)

        return ThirdViewHolder(view)
    }

    override fun onBindViewHolder(holder: ThirdViewHolder, position: Int) {
        holder.view.productListItem = categoryList[position]

    }

    override fun getItemCount(): Int = categoryList.size
}