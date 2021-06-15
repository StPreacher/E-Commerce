package com.mdgn.ecommerce.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mdgn.ecommerce.MainActivity
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.databinding.ActivitySignInBinding
import com.mdgn.ecommerce.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    private lateinit var binding : ActivitySignUpBinding
    private lateinit var eMail : String
    private lateinit var password : String
    private lateinit var userName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mAuth = FirebaseAuth.getInstance()

        binding.signUpButton.setOnClickListener {
            getTextFieldsInput()
            if(eMail.isNotEmpty() && password.isNotEmpty() && userName.isNotEmpty()){
                mAuth.createUserWithEmailAndPassword(eMail,password).addOnCompleteListener {
                    if (it.isSuccessful){
                        Log.w("Login", "signUpWithEmail:success")
                        val user : FirebaseUser? = mAuth.currentUser
                        updateUI(user)
                        val intent = Intent(this, MainActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(intent)
                        finish()
                    }else{
                        Log.w("Login", "signUpWithEmail:failure", it.exception)
                        Toast.makeText(this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
            }else{
                Toast.makeText(this,"Boxes can not be empty!!",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun getTextFieldsInput() {
        eMail = binding
            .eMailText
            .editText
            ?.text
            .toString()

        password = binding
            .passwordText
            .editText
            ?.text
            .toString()

        userName = binding
            .passwordText
            .editText
            ?.text
            .toString()
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null){
            Toast.makeText(this,"User is ${user.displayName}", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
        }else{
            Toast.makeText(this,"User is null", Toast.LENGTH_SHORT).show()
        }
    }
}