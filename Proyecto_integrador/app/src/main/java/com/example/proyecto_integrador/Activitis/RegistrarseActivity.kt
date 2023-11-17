package com.example.proyecto_integrador.Activitis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.proyecto_integrador.R


class RegistrarseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)


    }

    fun btnclick2(view: View) {
        setContentView(R.layout.activity_login)
    }
}