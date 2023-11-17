package com.example.proyecto_integrador.Activitis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.proyecto_integrador.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        splashScreen.setKeepOnScreenCondition{ true }

        Thread.sleep(800)
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}