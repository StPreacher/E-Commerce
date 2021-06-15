package com.mdgn.ecommerce.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mdgn.ecommerce.MainActivity
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignInBinding
    private lateinit var mAuth : FirebaseAuth
    private lateinit var eMail : String
    private lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        mAuth = FirebaseAuth.getInstance()

        //GO TO SIGN-UP
        binding.goToSignUpText.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
        }

        //SIGN-IN
        binding.signInButton.setOnClickListener {
            getTextFieldsInput()
            if (eMail.trim().length > 0 && password.trim().length > 0 ){
                mAuth.signInWithEmailAndPassword(eMail,password).addOnCompleteListener {
                    if (it.isSuccessful){
                        Log.w("Login", "signInWithEmail:success")
                        val user : FirebaseUser? = mAuth.currentUser
                        updateUI(user)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Log.w("Login", "signInWithEmail:failure", it.exception)
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
    }

    override fun onStart() {
        super.onStart()
        val currentUser : FirebaseUser? = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null){
            Toast.makeText(this,"User is $user",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
        }else{
            Toast.makeText(this,"User is null",Toast.LENGTH_SHORT).show()
        }
    }
}