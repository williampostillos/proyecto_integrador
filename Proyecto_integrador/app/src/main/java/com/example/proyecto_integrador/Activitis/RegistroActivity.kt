package com.example.proyecto_integrador.Activitis

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Request
import com.example.proyecto_integrador.R


class RegistroActivity : AppCompatActivity() {
    private val requestQueue by lazy { Volley.newRequestQueue(this) }
    private val url1 = "http://192.168.170.102:8080/android/save.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val edt_user: EditText = findViewById(R.id.edt_user)
        val edt_Email: EditText = findViewById(R.id.edt_Email)
        val edt_Password: EditText = findViewById(R.id.edt_Password)

        val txt_registro: TextView = findViewById(R.id.txt_registro)
        val btn_iniciarSesion: Button = findViewById(R.id.btn_iniciarSesion)

        txt_registro.setOnClickListener {
            val username = edt_user.text.toString().trim()
            val email = edt_Email.text.toString().trim()
            val password = edt_Password.text.toString().trim()

            if (validateInputs(username, email, password)) {
                createUser(username, email, password)
                home()
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            }
        }

        btn_iniciarSesion.setOnClickListener {
            login()
        }
    }

    private fun validateInputs(username: String, email: String, password: String): Boolean {
        return username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
    }

    private fun createUser(username: String, email: String, password: String) {
        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url1,
            Response.Listener<String> { response ->
                Toast.makeText(this@RegistroActivity, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
            },
            Response.ErrorListener { error ->
                // Manejar el error específico de Volley aquí
                Toast.makeText(
                    this@RegistroActivity,
                    "Error en la solicitud de registro: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["username"] = username
                params["email"] = email
                params["password"] = password
                return params
            }
        }

        requestQueue.add(stringRequest)
    }

    private fun home() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun login() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
