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

    fun getSecondCategories(childName:String,categoryID:Int){

        viewModelScope.launch {
            repository.getCategoryReference().let {
                it.child(childName).get().addOnSuccessListener {
                    if(it.exists()){
                        for (categorySnapshot in it.children){
                            val category = categorySnapshot.getValue(Kategori::class.java)
                            tempCategoryList.add(category?.alt_kategori?.get(categoryID)!!)
                        }
                        categoryList.value = tempCategoryList
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