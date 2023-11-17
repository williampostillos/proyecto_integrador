package com.example.proyecto_integrador.Activitis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_integrador.Activities.HomeFragment
import com.example.proyecto_integrador.Adapter.FutureAdapter
import com.example.proyecto_integrador.Domains.FutureDomain
import com.example.proyecto_integrador.R

class FutureFragment : Fragment() {

    private lateinit var adapterTomorrow: RecyclerView.Adapter<*>
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_future, container, false)

        initRecyclerView(view)
        setVariable(view)

        return view
    }

    private fun setVariable(view: View) {
        val backBtn: ImageView = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.frameContainer, HomeFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun initRecyclerView(view: View) {
        val items = ArrayList<FutureDomain>()

        items.add(FutureDomain("Sáb", "storm", "Tormenta", 25, 10))
        items.add(FutureDomain("Dom", "cloudy", "Nublado", 24, 16))
        items.add(FutureDomain("Lun", "windy", "Ventoso", 29, 15))
        items.add(FutureDomain("Mar", "cloudy_sunny", "Nublado soleado", 22, 13))
        items.add(FutureDomain("Mie", "sunny", "Soleado", 28, 11))
        items.add(FutureDomain("Jue", "rainy", "Lluvioso", 23, 12))

        recyclerView = view.findViewById(R.id.view2)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        adapterTomorrow = FutureAdapter(items)
        recyclerView.adapter = adapterTomorrow
    }
}
