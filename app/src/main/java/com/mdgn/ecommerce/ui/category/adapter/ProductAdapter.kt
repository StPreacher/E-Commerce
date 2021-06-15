package com.mdgn.ecommerce.ui.category.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.databinding.ProductDetailBinding
import com.mdgn.ecommerce.model.ProductDetailList
import com.mdgn.ecommerce.ui.category.AddToCartClickListener
import com.mdgn.ecommerce.util.getProgressDrawable
import com.mdgn.ecommerce.util.loadImage
import kotlinx.android.synthetic.main.product_detail.view.*

class ProductAdapter (var productDetailList: List<ProductDetailList>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>()
,AddToCartClickListener{

    private val mUser = FirebaseAuth.getInstance().currentUser
    private val mDatabase = FirebaseFirestore.getInstance()

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
        holder.view.listener = this
        holder.view.addToCartButton.setOnClickListener {
            val addedProducts = hashMapOf(
                "imageUrl" to productDetailList[position].product_image_url,
                "productName" to productDetailList[position].name,
                "productDetail" to productDetailList[position].detail,
                "productPrice" to productDetailList[position].price,
                "productID" to productDetailList[position].id
            )
            mDatabase.collection("users")
                .document(mUser?.uid.toString())
                .collection("cart")
                .document(productDetailList[position].id.toString())
                .set(addedProducts)
                .addOnSuccessListener { Log.d("AddCart", "DocumentSnapshot successfully written!")
                                        Toast.makeText(holder.view.root.context,"Successfully Added",Toast.LENGTH_SHORT).show()}
                .addOnFailureListener { e -> Log.w("AddCart", "Error writing document", e) }
        }
    }

    override fun getItemCount(): Int = productDetailList.size

    override fun onAddToCartClicked(v: View) {

        v.addToCartButton.setOnClickListener {
            val addedProducts = hashMapOf(
            "imageUrl" to v.productImage?.toString(),
            "productName" to v.productName?.text.toString(),
            "productDetail" to v.productDetails?.text.toString(),
            "productPrice" to v.productPrice?.text.toString(),
            "productID" to v.productID?.text.toString()
        )
            mDatabase.collection("users")
                .document(mUser?.uid.toString())
                .collection("cart")
                .document(v.productID.text.toString())
                .set(addedProducts)
                .addOnSuccessListener {
                    Log.d("AddCart", "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w("AddCart", "Error writing document", e) }
        }

    }
}


