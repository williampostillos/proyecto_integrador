package com.example.proyecto_integrador.Activitis

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.proyecto_integrador.R
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private val requestQueue by lazy { Volley.newRequestQueue(this) }
    private val url1 = "http://192.168.170.102:8080/android/login.php"

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txt_login: TextView = findViewById(R.id.txt_login)
        val btn_registrarse: Button = findViewById(R.id.btn_registrarse)
        val edtUsername: EditText = findViewById(R.id.edt_User)
        val edtPassword: EditText = findViewById(R.id.edt_Password)

        txt_login.setOnClickListener {
            val username = edtUsername.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            if (validateInputs(username, password)) {
                loginUser(username, password)
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }

        btn_registrarse.setOnClickListener {
            registrarse()
        }
    }

    private fun validateInputs(username: String, password: String): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }

    private fun loginUser(username: String, password: String) {
        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url1,
            Response.Listener<String> { response ->
                try {
                    val jsonResponse = JSONObject(response)

                    if (jsonResponse.getBoolean("success")) {
                        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@LoginActivity, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this@LoginActivity, "Error en el formato de la respuesta del servidor", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(
                    this@LoginActivity,
                    "Error en la solicitud de inicio de sesión: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["password"] = password
                return params
            }
        }

        requestQueue.add(stringRequest)
    }

    private fun registrarse() {
        val intent = Intent(this, RegistroActivity::class.java)
        startActivity(intent)
    }
}