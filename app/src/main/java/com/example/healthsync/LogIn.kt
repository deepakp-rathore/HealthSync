package com.example.healthsync

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.renderscript.ScriptGroup.Input
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isNotEmpty
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LogIn : AppCompatActivity() {
    lateinit var Database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        val lphone = findViewById<TextInputEditText>(R.id.phone)
        val lpassword = findViewById<TextInputEditText>(R.id.password)
        val loginBtn = findViewById<Button>(R.id.login)

        loginBtn.setOnClickListener {
            val phone = lphone.text.toString()
            val password = lpassword.text.toString()

            if (phone.isNotEmpty() && password.isNotEmpty()){
                loginVerification(phone, password)
            }else{
                Toast.makeText(this, "Please fill all Fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loginVerification(phone: String, password: String) {

        Database = FirebaseDatabase.getInstance().getReference("Users")
        Database.child(phone).get().addOnSuccessListener {
            if (it.exists()){
                if (it.child("password").value == password){
                    Toast.makeText(this, "Login Successful ", Toast.LENGTH_SHORT).show()
                    var textIntent = Intent(this, InputPage1::class.java)
                    startActivity(textIntent)
                }else{
                    Toast.makeText(this, "Incorrect Password ", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Invalid Phone or User", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Database Error Occurred", Toast.LENGTH_SHORT).show()
        }

    }
}