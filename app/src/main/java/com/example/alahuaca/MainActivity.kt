package com.example.alahuaca

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseAuth = FirebaseAuth.getInstance()
        var btningresar : Button = findViewById(R.id.btnIngresar)
        val txtemail : TextView = findViewById(R.id.edtEmail)
        val password : TextView = findViewById(R.id.edtPassword)
        firebaseAuth = Firebase.auth
        btningresar.setOnClickListener(){
            ingresar(txtemail.text.toString(),password.text.toString())
        }
    }
    private fun ingresar(email: String, password: String){
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {
            task ->
            if(task.isSuccessful){
                val user = firebaseAuth.currentUser
                Toast.makeText(baseContext, "Loading", Toast.LENGTH_SHORT).show()
                val i = Intent(this, Scrolling::class.java)
                startActivity(i)
            }
            else{
                Toast.makeText(baseContext,"Correo y/o contraseña incorrecta", Toast.LENGTH_SHORT).show()
            }
        }
    }
}