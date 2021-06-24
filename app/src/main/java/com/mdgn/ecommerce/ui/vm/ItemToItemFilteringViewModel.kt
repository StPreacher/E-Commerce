package com.mdgn.ecommerce.ui.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.mdgn.ecommerce.repo.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemToItemFilteringViewModel @Inject constructor(private val repository: CategoryRepository) : ViewModel() {

    private val database : FirebaseFirestore = FirebaseFirestore.getInstance()
    private var userIDTempList = mutableListOf<String>()
    private var ratedProductsTempList = mutableListOf<String>()
    var userIDList = mutableListOf<String>()
    var ratedProductsList = mutableListOf<String>()


    fun getProductMatrixFromFirestore(collectionName : String){
        userIDTempList.clear()
        ratedProductsTempList.clear()
        getUserIDList()
        viewModelScope.launch {
            val docRef = database.collection(collectionName)
            docRef.get().addOnSuccessListener {
                if (!it.isEmpty){
                    for (productSnapshot in it.documents){
//                        Log.d("GotRated", productSnapshot.id)
//                        var key = it.documents[0]["eFlJ9poNnOfC6ksVfWzYmx2ZzHC2"]
//                        Log.d("GotRated", key.toString())
                        val ratedProduct = productSnapshot.id
                        ratedProductsTempList.add(ratedProduct)
                    }
                    ratedProductsList = ratedProductsTempList
//                    Log.d("GotRated", ratedProductsList.toList().toString())

                    var matrix = MutableList(userIDList.size) { MutableList(ratedProductsList.size) {0.0}}

                    //Burasi deneme icin
                    matrix[0][5] = 3.5



                    for (i in 0 until userIDList.size){
                        for (j in 0 until ratedProductsList.size)
                            Log.d("GotRated", "$i - $j = ${matrix[i][j]}")
                    }
                    for (rateSnapshot in it.documents){
//                        matrix[ratedProductsList.indexOf(rateSnapshot.id)][userIDList.indexOf(rateSnapshot[rateSnapshot.id].)]

                    }


            }
            }
        }

    }

    private fun getUserIDList() {
        val docRef = database.collection("users")
        docRef.get().addOnSuccessListener {
            for (userSnapshot in it.documents){
                val userID = userSnapshot.id
                userIDTempList.add(userID)

            }
            userIDList = userIDTempList
            //Log.d("GotRated", userIDList.toList().toString())
        }
    }

}

