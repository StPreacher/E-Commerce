package com.mdgn.ecommerce.ui.category


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.databinding.BaseRowCategoryBinding
import com.mdgn.ecommerce.model.Kategori
import com.mdgn.ecommerce.util.getProgressDrawable
import com.mdgn.ecommerce.util.loadImage

class CategoryAdapter(var categoryList : List<Kategori>)
    : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(),CategoryClickListener {

    inner class ViewHolder(var view : BaseRowCategoryBinding)
        : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<BaseRowCategoryBinding>(inflater, R.layout.base_row_category,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.view.baseKategori = categoryList[position]
        holder.view.categoryImageView.loadImage(categoryList[position]
            .url, getProgressDrawable(holder
            .view
            .categoryImageView
            .context))

        holder.view.listener = this

    }

    override fun getItemCount(): Int = categoryList.size

    override fun onCategoryClicked(v: View) {
        val categoryID =v.id
        val action = CategoryFragmentDirections.actionCategoryFragmentToSecondCategoryFragment(categoryID)
        Navigation.findNavController(v).navigate(action)
    }


}