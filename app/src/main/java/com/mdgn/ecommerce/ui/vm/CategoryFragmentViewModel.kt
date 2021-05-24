package com.mdgn.ecommerce.ui.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdgn.ecommerce.model.Kategori
import com.mdgn.ecommerce.model.KategoriList
import com.mdgn.ecommerce.repo.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryFragmentViewModel @Inject constructor(private val repository: CategoryRepository) : ViewModel(){

    val categoryList = MutableLiveData<KategoriList?>()

    fun getAllCategories(childName : String) {
        viewModelScope.launch {

            repository.getCategoryReference().let {
                it.child(childName).get().addOnSuccessListener {

                    categoryList.value = KategoriList(it.value as List<Kategori>)

                }.addOnFailureListener{
                    categoryList.value = null
                    Log.d("Response", it.localizedMessage)
                }
            }

        }

    }


}