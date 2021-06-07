package com.mdgn.ecommerce.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.databinding.ThirdRowCategoryBinding
import com.mdgn.ecommerce.model.ProductList
import com.mdgn.ecommerce.ui.category.CategoryClickListener
import com.mdgn.ecommerce.ui.category.ThirdCategoryFragmentDirections
import kotlinx.android.synthetic.main.third_row_category.view.*

class ThirdCategoryAdapter (var categoryList : List<ProductList>,
                            var categoryID: Int?,
                            var altCategoryID : Int?)
    : RecyclerView.Adapter<ThirdCategoryAdapter.ThirdViewHolder>(), CategoryClickListener {


    inner class ThirdViewHolder (val view : ThirdRowCategoryBinding) : RecyclerView.ViewHolder(view.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThirdViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ThirdRowCategoryBinding>(inflater, R.layout.third_row_category,parent,false)

        return ThirdViewHolder(view)
    }

    override fun onBindViewHolder(holder: ThirdViewHolder, position: Int) {
        holder.view.productListItem = categoryList[position]
        holder.view.listener = this
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onCategoryClicked(v: View) {
        val altAltCategoryID = v.altAltCategoryID.text.toString().toInt()
        val action = ThirdCategoryFragmentDirections
            .actionThirdFragmentToProductPageFragment(categoryID!!,altCategoryID!!,altAltCategoryID)
        Navigation.findNavController(v).navigate(action)
    }
}