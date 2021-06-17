package com.mdgn.ecommerce.ui.category.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.databinding.ProductDetailBinding
import com.mdgn.ecommerce.model.ProductDetailList
import com.mdgn.ecommerce.util.getProgressDrawable
import com.mdgn.ecommerce.util.loadImage

class ProductAdapter (var productDetailList: List<ProductDetailList>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

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
        //Add to cart
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
        //Rating jobs
        holder.view.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            val ratings = hashMapOf(
                mUser?.uid to rating
            )

            mDatabase.collection("rated_products")
                .document(productDetailList[position].id.toString())
                .set(ratings, SetOptions.merge())
                .addOnSuccessListener { Log.d("Rating", "DocumentSnapshot successfully written!")
                    Toast.makeText(holder.view.root.context,"Successfully Rated",Toast.LENGTH_SHORT).show() }
                .addOnFailureListener { e -> Log.w("Rating", "Error writing document", e) }
        }

    }

    override fun getItemCount(): Int = productDetailList.size

}


