package com.mdgn.ecommerce.ui.category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.model.Kategori
import com.mdgn.ecommerce.model.KategoriList


class CategoryFragment : Fragment() {

    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = FirebaseDatabase.getInstance().reference

        database.child("kategori").get().addOnSuccessListener {

            val response = KategoriList(it.value as List<Kategori>)

            Log.d("Response","$response")
        }.addOnFailureListener {
            Log.d("Response", it.localizedMessage)
        }


    }





}