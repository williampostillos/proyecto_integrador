package com.example.proyecto_integrador.Activitis

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.android.volley.Header
import com.example.proyecto_integrador.R
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import com.loopj.android.http.RequestParams
import org.json.JSONObject
import com.google.gson.Gson

class MostrarAlertasFragment : Fragment() {

    private val APP_ID = "2306b4e5ddce5da1dbdfadc0038ecac0"
    private val WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather"
    private val MIN_TIME: Long = 5000
    private val MIN_DISTANCE = 1000f
    private val REQUEST_CODE = 101

    private var locationProvider = LocationManager.GPS_PROVIDER

    private var nameofCity: TextView? = null
    private var weatherState: TextView? = null
    private var temperature: TextView? = null
    private var weatherIcon: ImageView? = null
    private var cityFinder: RelativeLayout? = null

    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_mostrar_alertas, container, false)

        // Inicializar vistas
        nameofCity = view.findViewById(R.id.cityName)
        weatherState = view.findViewById(R.id.weatherCondition)
        temperature = view.findViewById(R.id.temperature)
        weatherIcon = view.findViewById(R.id.weatherIcon)
        cityFinder = view.findViewById(R.id.cityFinder)

        cityFinder?.setOnClickListener {
            val alertasFragment = AlertasFragment()

            // Iniciar la transacción del fragmento
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            // Reemplazar el fragmento actual con el fragmento de AlertasFragment
            transaction.replace(R.id.frameContainer, alertasFragment)

            // Añadir la transacción a la pila para que el botón de retroceso funcione correctamente
            transaction.addToBackStack(null)

            // Confirmar la transacción
            transaction.commit()
        }



        return view
    }

    override fun onResume() {
        super.onResume()
        val mIntent: Intent = requireActivity().intent
        val city = mIntent.getStringExtra("City")
        if (city != null) {
            getWeatherForNewCity(city)
        } else {
            getWeatherForCurrentLocation()
        }
    }


    private fun getWeatherForNewCity(city: String) {
        val params = RequestParams()
        params.put("q", city)
        params.put("appid", APP_ID)
        letsdoSomeNetworking(params)
    }

    private fun getWeatherForCurrentLocation() {
        locationManager = requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                val latitude = location.latitude.toString()
                val longitude = location.longitude.toString()
                val params = RequestParams()
                params.put("lat", latitude)
                params.put("lon", longitude)
                params.put("appid", APP_ID)
                letsdoSomeNetworking(params)
            }

            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {
                // No se puede obtener la ubicación
            }
        }
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE
            )
            return
        }
        locationManager?.requestLocationUpdates(
            locationProvider,
            MIN_TIME,
            MIN_DISTANCE,
            locationListener as LocationListener
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    requireActivity(),
                    "Ubicación obtenida exitosamente",
                    Toast.LENGTH_SHORT
                ).show()
                getWeatherForCurrentLocation()
            } else {
                // El usuario denegó el permiso
                Toast.makeText(
                    requireActivity(),
                    "La aplicación necesita permisos de ubicación para obtener datos del clima.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun letsdoSomeNetworking(params: RequestParams) {
        val client = AsyncHttpClient()
        client.get(WEATHER_URL, params, object : JsonHttpResponseHandler() {
            fun onSuccess(
                statusCode: Int,
                headers: Array<Header>?,
                response: JSONObject?
            ) {
                Toast.makeText(requireActivity(), "Datos obtenidos con éxito", Toast.LENGTH_SHORT).show()

                val gson = Gson()
                val jsonString = response?.toString()
                val weatherData: WeatherData = gson.fromJson(jsonString, WeatherData::class.java)

                updateUI(weatherData)
            }

            fun onFailure(
                statusCode: Int,
                headers: Array<Header>?,
                throwable: Throwable?,
                errorResponse: JSONObject?
            ) {
                // Manejar el fallo
                Log.e("Networking", "Error: $statusCode", throwable)
                Toast.makeText(
                    requireActivity(),
                    "Error obteniendo datos del clima. Por favor, inténtelo de nuevo.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    private fun updateUI(weather: WeatherData) {
        temperature?.text = weather.temperature
        nameofCity?.text = weather.city
        weatherState?.text = weather.weatherType
        val resourceID = resources.getIdentifier(weather.icon, "drawable", requireActivity().packageName)
        weatherIcon?.setImageResource(resourceID)
    }

    override fun onPause() {
        super.onPause()
        locationListener?.let { locationManager?.removeUpdates(it) }
    }

}
