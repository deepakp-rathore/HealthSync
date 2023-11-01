package com.example.healthsync

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val etname = findViewById<TextInputEditText>(R.id.name)
        val etemail = findViewById<TextInputEditText>(R.id.email)
        val etphone = findViewById<TextInputEditText>(R.id.phone)
        val etpassword = findViewById<TextInputEditText>(R.id.password)
        val signbtn = findViewById<Button>(R.id.signup)



        signbtn.setOnClickListener{
            val name = etname.text.toString()
            val email = etemail.text.toString()
            val phone = etphone.text.toString()
            val password = etpassword.text.toString()

            val user = User(name,email,phone,password)
            database = FirebaseDatabase.getInstance().getReference("Users")

            database.child(phone).setValue(user).addOnSuccessListener {
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
                val inputOneIntent = Intent(this, InputPage1::class.java)
                startActivity(inputOneIntent)
            }.addOnSuccessListener {
                Toast.makeText(this, "Error Occurred", Toast.LENGTH_SHORT).show()
            }
        }

        val login = findViewById<TextView>(R.id.AlreadyAccount)
        login.setOnClickListener {
            val loginIntent = Intent(this, LogIn::class.java)
            startActivity(loginIntent)
        }


    }
}