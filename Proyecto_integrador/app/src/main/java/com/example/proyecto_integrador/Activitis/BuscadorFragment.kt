package com.example.proyecto_integrador.Activitis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.proyecto_integrador.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class BuscadorFragment : Fragment(), OnMapReadyCallback {

    private lateinit var txt_latitud: EditText
    private lateinit var txt_longitud: EditText
    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_buscador, container, false)
        mapeo(view)
        return view
    }

    private fun mapeo(view: View) {
        txt_latitud = view.findViewById(R.id.txt_latitud)
        txt_longitud = view.findViewById(R.id.txt_longitud)

        // Obtén el SupportMapFragment
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Resto del código de mapeo...
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val peru = LatLng(-12.0262542, -77.1525881)
        mMap.addMarker(MarkerOptions().position(peru).title("Perú"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(peru))

        mMap.setOnMapClickListener { latLng ->
            txt_latitud.setText("${latLng.latitude}")
            txt_longitud.setText("${latLng.longitude}")

            mMap.clear()
            val peru = LatLng(latLng.latitude, latLng.longitude)
            mMap.addMarker(MarkerOptions().position(peru).title(""))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(peru))
        }

        mMap.setOnMapLongClickListener { latLng ->
            txt_latitud.setText("${latLng.latitude}")
            txt_longitud.setText("${latLng.longitude}")

            mMap.clear()
            val peru = LatLng(latLng.latitude, latLng.longitude)
            mMap.addMarker(MarkerOptions().position(peru).title(""))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(peru))
        }

    }
}