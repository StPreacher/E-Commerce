package com.mdgn.ecommerce.ui.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdgn.ecommerce.model.Kategori
import com.mdgn.ecommerce.repo.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryFragmentViewModel @Inject constructor(private val repository: CategoryRepository) : ViewModel(){

    val categoryList = MutableLiveData<List<Kategori>>()
    private val tempCategoryList = mutableListOf<Kategori>()

    fun getAllCategories(childName : String) {
        tempCategoryList.clear()
        viewModelScope.launch {
            repository.getCategoryReference().let { it ->
                it.child(childName).get().addOnSuccessListener {

                    if(it.exists()){
                        for (categorySnapshot in it.children){
                            val category = categorySnapshot.getValue(Kategori::class.java)
                            tempCategoryList.add(category!!)
                        }
                        categoryList.value = tempCategoryList

                    }

                }.addOnFailureListener{
                    categoryList.value = mutableListOf()
                    Log.d("Response", it.localizedMessage)
                }
            }

        }

    }



}