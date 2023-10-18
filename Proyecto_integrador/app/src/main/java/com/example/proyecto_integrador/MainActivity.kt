package com.example.proyecto_integrador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        splashScreen.setKeepOnScreenCondition{ true }

        Thread.sleep(800)
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
        finish()
    }
}