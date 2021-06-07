package com.mdgn.ecommerce.ui.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.databinding.ProductDetailBinding
import com.mdgn.ecommerce.model.ProductDetailList
import com.mdgn.ecommerce.util.getProgressDrawable
import com.mdgn.ecommerce.util.loadImage

class ProductAdapter (var productDetailList: List<ProductDetailList>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder (val view : ProductDetailBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ProductDetailBinding>(
            inflater,
            R.layout.product_detail,
            parent,
            false
        )

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.view.productDetail = productDetailList[position]
        holder.view.productImage.loadImage(productDetailList[position]
            .product_image_url, getProgressDrawable(
            holder.view.productImage.context
            ))
    }

    override fun getItemCount(): Int = productDetailList.size
}


