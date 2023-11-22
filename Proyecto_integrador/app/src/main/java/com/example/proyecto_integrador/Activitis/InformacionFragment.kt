package com.example.proyecto_integrador.Activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_integrador.Activitis.DetalleFragment
import com.example.proyecto_integrador.Adapter.InformacionAdapter
import com.example.proyecto_integrador.R
import com.example.proyecto_integrador.data.Detalle

class InformacionFragment : Fragment() {

    private lateinit var AdapterInformacion: InformacionAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_informacion, container, false)
        initRecyclerView(view)
        return view
    }

    private fun initRecyclerView(view: View) {
        val items = ArrayList<Detalle>()

        items.add(Detalle((R.drawable.img1), "Fenómeno El Niño", "De click Aquí para más información"))
        items.add(Detalle((R.drawable.img2), "El Ciclón Yaku", "De click Aquí para más información"))
        items.add(Detalle((R.drawable.img3), "El Ciclón Yaku", "De click Aquí para más información"))
        items.add(Detalle((R.drawable.img3), "El Ciclón Yaku", "De click Aquí para más información"))

        recyclerView = view.findViewById(R.id.view3)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        AdapterInformacion = InformacionAdapter(items) { detalle ->
            detalle?.let {
                val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                transaction.replace(R.id.frameContainer, DetalleFragment())
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }
        recyclerView.adapter = AdapterInformacion
    }
}

