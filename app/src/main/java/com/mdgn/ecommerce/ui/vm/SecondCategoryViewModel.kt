package com.mdgn.ecommerce.ui.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdgn.ecommerce.model.AltKategori
import com.mdgn.ecommerce.model.Kategori
import com.mdgn.ecommerce.repo.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondCategoryViewModel @Inject constructor(private val repository: CategoryRepository) : ViewModel() {

    val categoryList = MutableLiveData<List<AltKategori>>()
    private val tempCategoryList = mutableListOf<AltKategori>()

    fun getSecondCategories(childName1:String,childName2:String,categoryId :Int){

        viewModelScope.launch {
            repository.getCategoryReference().let { it ->
                it.child(childName1).child(categoryId.toString()).child(childName2).get().addOnSuccessListener {
                    if(it.exists()){
                        for (categorySnapshot in it.children){
                            val category = categorySnapshot.getValue(AltKategori::class.java)
                            tempCategoryList.add(category!!)
                        }
                        categoryList.value = tempCategoryList
                        Log.d("Response", tempCategoryList.toList().toString())
                    }
                }.addOnFailureListener {
                    categoryList.value = mutableListOf()
                    Log.d("Response", it.localizedMessage)
                }
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        tempCategoryList.clear()
    }

}