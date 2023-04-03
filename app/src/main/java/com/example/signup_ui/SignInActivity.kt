package com.example.signup_ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class SignInActivity : AppCompatActivity() {

    companion object {
        const val KEY1 = "com.example.signup_ui.SignInActivity.KEY1"
        const val KEY2 = "com.example.signup_ui.SignInActivity.KEY2"
        const val KEY3 = "com.example.signup_ui.SignInActivity.KEY3"
    }

    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val uniqueID = findViewById<TextInputEditText>(R.id.ID)
        val pwd = findViewById<TextInputEditText>(R.id.pwd1)
        val signIn = findViewById<Button>(R.id.btnsignin)

        signIn.setOnClickListener {
            val id = uniqueID.text.toString()
            val pwd1 = pwd.text.toString()

            if(!id.isEmpty() && !pwd1.isEmpty()) {
                readdata(id,pwd1)
            } else {
                Toast.makeText(this,"Fill the field first!",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readdata(id: String, pwd1: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")

        database.child(id).get().addOnSuccessListener {
            if(it.exists()) {
                val password = it.child("password").value.toString()
                if(password.equals(pwd1)) {
                    val userName = it.child("name").value.toString()
                    val email = it.child("email").value.toString()

                    val intent = Intent(applicationContext,WelcomeActivity::class.java)
                    intent.putExtra(KEY1,userName)
                    intent.putExtra(KEY2,email)
                    intent.putExtra(KEY3,id)
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"Invalid Password!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"User does not exists",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}