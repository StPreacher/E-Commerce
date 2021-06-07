package com.mdgn.ecommerce.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdgn.ecommerce.model.ProductDetailList
import com.mdgn.ecommerce.repo.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val repository: CategoryRepository) : ViewModel()  {

    val productList = MutableLiveData<List<ProductDetailList>>()
    private val tempList = mutableListOf<ProductDetailList>()

    fun getProductDetails(child1:String,
                          child2:String,
                          child3:String,
                          child4:String,
                          categoryID:Int,
                          altCategoryID:Int,
                          altAltCategoryID:Int){
        tempList.clear()
        viewModelScope.launch {
            repository.getCategoryReference()
                .child(child1)
                .child(categoryID.toString())
                .child(child2)
                .child(altCategoryID.toString())
                .child(child3)
                .child(altAltCategoryID.toString())
                .child(child4)
                .get()
                .addOnSuccessListener {
                    if(it.exists()){
                        for (categorySnapshot in it.children){
                            val category = categorySnapshot.getValue(ProductDetailList::class.java)
//                            val category = categorySnapshot.child("detail").getValue(ProductDetailList::class.java)
                            tempList.add(category!!)
                        }
                        productList.value = tempList
                    }
                }.addOnFailureListener {
                    productList.value = mutableListOf()
                }
        }
    }

}