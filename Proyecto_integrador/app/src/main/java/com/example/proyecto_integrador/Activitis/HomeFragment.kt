package com.example.proyecto_integrador.Activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_integrador.Activitis.FutureFragment
import com.example.proyecto_integrador.Adapter.HourlyAdapters
import com.example.proyecto_integrador.Domains.Hourly
import com.example.proyecto_integrador.R
import com.google.android.material.textview.MaterialTextView

class HomeFragment : Fragment() {

    private lateinit var adapterHourly: RecyclerView.Adapter<*>
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // ...
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        initRecyclerView(view)
        setVariable(view)
        return view
    }

    private fun setVariable(view: View) {
        val next7dayBtn: MaterialTextView = view.findViewById(R.id.nextBtn)
        next7dayBtn.setOnClickListener {
            val futureFragment = FutureFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.frameContainer, futureFragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }


    private fun initRecyclerView(view: View) {
        val items = ArrayList<Hourly>()

        items.add(Hourly("9 pm", 28, "cloudy"))
        items.add(Hourly("11 pm", 29, "sunny"))
        items.add(Hourly("12 pm", 30, "wind"))
        items.add(Hourly("1 am", 29, "rainy"))
        items.add(Hourly("2 am", 27, "storm"))

        recyclerView = view.findViewById(R.id.view1)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        adapterHourly = HourlyAdapters(items)
        recyclerView.adapter = adapterHourly
    }
}
