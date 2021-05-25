package com.mdgn.ecommerce.ui.category


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.databinding.BaseRowCategoryBinding
import com.mdgn.ecommerce.model.Kategori
import com.mdgn.ecommerce.util.getProgressDrawable
import com.mdgn.ecommerce.util.loadImage

class CategoryAdapter(var categoryList : List<Kategori>,itemClick : (position : Int) -> Unit)
    : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(),CategoryClickListener {

    //TODO En son resime degilde bos yere tiklanildiginda click calisiyor, sorun bu

    private val itemClick = itemClick

    inner class ViewHolder(var view : BaseRowCategoryBinding, itemClick : (position : Int) -> Unit)
        : RecyclerView.ViewHolder(view.root) {

            init {
                view.root.setOnClickListener {
                    itemClick(adapterPosition)
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        val inflater =LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<BaseRowCategoryBinding>(inflater, R.layout.base_row_category,parent,false)

        return ViewHolder(view,itemClick)
    }

    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        holder.view.baseKategori = categoryList[position]
        holder.view.categoryImageView.loadImage(categoryList[position]
            .url, getProgressDrawable(holder
            .view
            .categoryImageView
            .context))

    }

    override fun getItemCount(): Int = categoryList.size

    override fun onCategoryClicked(view: View) {
//        val categoryID =view.id )
//        val action = CategoryFragmentDirections.actionCategoryFragmentToSecondCategoryFragment(categoryID)
//        Navigation.findNavController(view).navigate(action)
    }


}