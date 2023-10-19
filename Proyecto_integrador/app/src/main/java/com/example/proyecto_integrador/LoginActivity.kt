package com.example.proyecto_integrador

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txt_login: TextView = findViewById(R.id.txt_login)
        val btn_registrarse: Button=findViewById(R.id.btn_registrarse)


        txt_login.setOnClickListener {
            login()
        }


        btn_registrarse.setOnClickListener{
            registrarse()
        }


    }
    private fun registrarse(){
        val intent =Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }
    private fun login(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }
}