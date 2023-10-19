package com.example.proyecto_integrador

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class RegistroActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val txt_registro: TextView = findViewById(R.id.txt_registro)
        val btn_iniciarSesion: Button = findViewById(R.id.btn_iniciarSesion)


        txt_registro.setOnClickListener {
            home()
        }

        btn_iniciarSesion.setOnClickListener{
            login()
        }

    }
    private fun home(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun login(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}