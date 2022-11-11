package com.example.alahuaca

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Registrar : AppCompatActivity() {
    private lateinit var  firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)

        val txtnombres : TextView = findViewById(R.id.edtNombre)
        val txtapellidos : TextView = findViewById(R.id.edtApellidos)
        val txtnewemail : TextView = findViewById(R.id.etrEmail)
        val txtpassword1 : TextView = findViewById(R.id.newPassword)
        val txtpassword2: TextView = findViewById(R.id.cfmPassword)
        val btnCrear : Button = findViewById(R.id.buttonCrear)
        btnCrear.setOnClickListener(){
            var pass1 = txtpassword1.text.toString()
            var pass2 = txtpassword2.text.toString()
            if(pass1.equals(pass2)){
                CrearCuenta(txtnewemail.text.toString(), txtpassword1.text.toString())
            }
            else{
                Toast.makeText(baseContext, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                txtpassword1.requestFocus()
            }
        }


        firebaseAuth = Firebase.auth
    }
    private fun CrearCuenta(email: String, password:String){
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
            task ->
            if(task.isSuccessful){
                Toast.makeText(baseContext, "Su registro se realizó satisfactoriamente", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(baseContext, "Revise los datos nuevamente: " + task.exception, Toast.LENGTH_SHORT).show()
            }
        }
    }
}