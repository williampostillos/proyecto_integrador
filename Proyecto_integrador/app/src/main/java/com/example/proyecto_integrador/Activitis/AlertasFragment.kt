package com.example.proyecto_integrador.Activitis

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.proyecto_integrador.R

class AlertasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_alertas, container, false)

        val editText: EditText = view.findViewById(R.id.searchCity)
        val backButton: ImageView = view.findViewById(R.id.backButton)

        backButton.setOnClickListener {
            activity?.finish()
        }

        editText.setOnEditorActionListener { v, actionId, event ->
            val newCity = editText.text.toString()

            // Crear una instancia del fragmento que deseas mostrar
            val mostrarAlertasFragment = MostrarAlertasFragment()

            // Crear un Bundle para pasar datos al fragmento
            val bundle = Bundle()
            bundle.putString("City", newCity)
            mostrarAlertasFragment.arguments = bundle

            // Iniciar la transacción del fragmento
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()

            // Reemplazar el fragmento actual con el nuevo fragmento
            transaction.replace(R.id.frameContainer, mostrarAlertasFragment)

            // Añadir la transacción a la pila para que el botón de retroceso funcione correctamente
            transaction.addToBackStack(null)

            // Confirmar la transacción
            transaction.commit()

            false
        }

        return view
    }
}