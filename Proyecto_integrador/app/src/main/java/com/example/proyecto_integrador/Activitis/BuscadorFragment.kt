package com.example.proyecto_integrador.Activitis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.proyecto_integrador.R
import com.example.proyecto_integrador.databinding.FragmentBuscadorBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.json.JSONObject


class BuscadorFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentBuscadorBinding
    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBuscadorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtén el SupportMapFragment
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Obtén la información meteorológica para la ciudad de Lima
        getJSONDataByname("Lima")
    }
    private fun getJSONDataByname(cityname: String) {
        val API_KEY = "b8c4acbdc7b133df1986cb69732b5dac"

        val queue = Volley.newRequestQueue(requireContext())
        val url = "https://api.openweathermap.org/data/2.5/weather?q=${cityname}&appid=${API_KEY}"

        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                setValues(response)
            },
            Response.ErrorListener {
                // Handle error
            })

        queue.add(jsonRequest)
    }
    private fun setValues(response: JSONObject) {
        val lat = response.getJSONObject("coord").getString("lat")
        val long = response.getJSONObject("coord").getString("lon")

        var maxtemp=response.getJSONObject("main").getString("temp_max")
        maxtemp=((((maxtemp).toFloat()-273.15)).toInt()).toString()
        binding.maxTemp.text=maxtemp+"°C"

        var mintemp=response.getJSONObject("main").getString("temp_min")
        mintemp=((((mintemp).toFloat()-273.15)).toInt()).toString()
        binding.minTemp.text=mintemp+"°C"

        binding.humidity.text = response.getJSONObject("main").getString("humidity") + "%"
    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val peru = LatLng(-12.0262542, -77.1525881)
        mMap.addMarker(MarkerOptions().position(peru).title("Perú"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(peru))

        mMap.setOnMapClickListener { latLng ->


            mMap.clear()
            val peru = LatLng(latLng.latitude, latLng.longitude)
            mMap.addMarker(MarkerOptions().position(peru).title(""))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(peru))
        }

        mMap.setOnMapLongClickListener { latLng ->


            mMap.clear()
            val peru = LatLng(latLng.latitude, latLng.longitude)
            mMap.addMarker(MarkerOptions().position(peru).title(""))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(peru))
        }

        fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            // Acceder a las vistas utilizando binding

            val lat = arguments?.getString("lat")
            val long = arguments?.getString("long")

        }
    }
}