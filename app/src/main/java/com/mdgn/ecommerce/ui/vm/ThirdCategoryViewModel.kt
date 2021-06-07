package com.mdgn.ecommerce.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdgn.ecommerce.model.ProductList
import com.mdgn.ecommerce.repo.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThirdCategoryViewModel @Inject constructor(private val repository: CategoryRepository) : ViewModel() {

    val categoryList = MutableLiveData<List<ProductList>>()
    private val tempCategoryList = mutableListOf<ProductList>()

    fun getThirdCategories(child1:String,child2:String,child3:String,categoryID:Int,altCategoryID:Int){
        tempCategoryList.clear()
        viewModelScope.launch {
            repository.getCategoryReference()
                .child(child1)
                .child(categoryID.toString())
                .child(child2)
                .child(altCategoryID.toString())
                .child(child3)
                .get()
                .addOnSuccessListener {
                    if(it.exists()){
                        for (categorySnapshot in it.children){
                            val category = categorySnapshot.getValue(ProductList::class.java)
                            tempCategoryList.add(category!!)
                        }
                        categoryList.value = tempCategoryList
                    }
                }.addOnFailureListener {
                        categoryList.value = mutableListOf()
                }
        }
    }

}