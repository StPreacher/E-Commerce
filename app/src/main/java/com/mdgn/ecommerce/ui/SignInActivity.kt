package com.mdgn.ecommerce.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.mdgn.ecommerce.MainActivity
import com.mdgn.ecommerce.R
import com.mdgn.ecommerce.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignInBinding
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val eMailText = findViewById<View>(R.id.eMailText) as EditText
        val passwordText = findViewById<View>(R.id.passwordText) as EditText

        val eMail = eMailText.text.toString()
        val password = passwordText.text.toString()

        mAuth = FirebaseAuth.getInstance()

        //GO TO SIGN-UP
        binding.goToSignUpText.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
        }

        //SIGN-IN
        binding.signInButton.setOnClickListener {
            if (eMail.isNotEmpty() && password.isNotEmpty()){
                mAuth.signInWithEmailAndPassword(eMail,password).addOnCompleteListener {
                    if (it.isSuccessful){
                        Log.w("Login", "createUserWithEmail:success")
                        val user : FirebaseUser? = mAuth.currentUser
                        updateUI(user)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Log.w("Login", "createUserWithEmail:failure", it.exception)
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

    override fun onStart() {
        super.onStart()
        val currentUser : FirebaseUser? = mAuth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null){
            startActivity(Intent(this,MainActivity::class.java))
        }else{
            Toast.makeText(this,"User is null",Toast.LENGTH_SHORT).show()
        }
    }
}