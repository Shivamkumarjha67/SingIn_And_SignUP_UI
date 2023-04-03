package com.example.signup_ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.text.buildSpannedString
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var firebase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signUpbtn = findViewById<Button>(R.id.btnsignup)
        val name = findViewById<TextInputEditText>(R.id.name)
        val uniqueId = findViewById<TextInputEditText>(R.id.id)
        val email = findViewById<TextInputEditText>(R.id.email)
        val pwd = findViewById<TextInputEditText>(R.id.password)
        val logInTxt = findViewById<TextView>(R.id.logIn)

        var msg : String = logInTxt.text.toString()

        var spannableStringVar = setUnderLine(msg)
        logInTxt.setText(spannableStringVar)

        logInTxt.setOnClickListener {
            var intent = Intent(applicationContext,SignInActivity::class.java)
            startActivity(intent)
        }

        signUpbtn.setOnClickListener {
            val username = name.text.toString()
            val id = uniqueId.text.toString()
            val emailId = email.text.toString()
            val pwd1 = pwd.text.toString()

//            val newEmailID : String = encodeString(emailId)

            firebase = FirebaseDatabase.getInstance().getReference("Users")

            val users = UsersClass(username,id,emailId,pwd1)

            firebase.child(id).setValue(users).addOnSuccessListener {

                name.setText("")
                uniqueId.setText("")
                email.setText("")
                pwd.setText("")

                Toast.makeText(this, "User registered Successfully",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener() {
                Toast.makeText(this,"Failed to registered",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUnderLine(msg: String) : SpannableString {
        val mspannableString = SpannableString(msg)

        mspannableString.setSpan(UnderlineSpan(),0,msg.length,0)
        return mspannableString
    }

//    private fun encodeString(emailId: String): String {
//        var encodededEmail = ""
//
//        for(i in 0 until emailId.length) {
//            var c : Char = emailId.get(i)
//            if(c == '.') c = ','
//
//            encodededEmail += c
//        }
//
//        return encodededEmail
//    }
}