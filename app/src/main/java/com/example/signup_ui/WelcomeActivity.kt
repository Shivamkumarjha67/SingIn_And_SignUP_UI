package com.example.signup_ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val name = intent.getStringExtra(SignInActivity.KEY1)

        var Wlctext = findViewById<TextView>(R.id.txtWelcome)
        val txtWlcm = Wlctext.text.toString() + " " + name

        Wlctext.setText(txtWlcm)

        val email = intent.getStringExtra(SignInActivity.KEY2)
        val uniqueID = intent.getStringExtra(SignInActivity.KEY3)

        val mail = findViewById<TextView>(R.id.Email)
        val id = findViewById<TextView>(R.id.uniqueID1)
        val userName = findViewById<TextView>(R.id.name1)

        userName.setText("Name is : $name")
        mail.setText("Email is : $email")
        id.setText("ID is : $uniqueID")
    }
}