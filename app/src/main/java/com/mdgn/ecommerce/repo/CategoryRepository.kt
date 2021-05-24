package com.mdgn.ecommerce.repo

import com.google.firebase.database.DatabaseReference
import javax.inject.Inject


class CategoryRepository @Inject constructor(private val reference: DatabaseReference){

    suspend fun getCategoryReference() : DatabaseReference {
        return reference
    }

}