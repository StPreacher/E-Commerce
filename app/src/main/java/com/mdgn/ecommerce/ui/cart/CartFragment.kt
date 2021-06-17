package com.mdgn.ecommerce.ui.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.mdgn.ecommerce.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private lateinit var binding : FragmentCartBinding
    private lateinit var mDatabase : FirebaseFirestore
    private lateinit var mAuth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater,container,false)
        val view = binding.root
        mDatabase = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()
        return view
    }
    //TODO Sepette urunleri listeleme islemi yapilacak
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val docRef = mDatabase
            .collection("users")
            .document(mAuth.currentUser?.uid.toString())
            .collection("cart")

        docRef.get().addOnSuccessListener {
            if (it != null){
                Log.d("GetCart", "Products of cart : ${it.documents}")
            }else{
                Log.d("GetCart", "No such document")

            }
        }.addOnFailureListener {
            Log.d("GetCart", "get failed with ", it)

        }

    }


}