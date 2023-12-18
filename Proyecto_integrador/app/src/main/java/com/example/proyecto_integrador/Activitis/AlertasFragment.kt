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
import com.example.proyecto_integrador.databinding.FragmentAlertasBinding
import org.json.JSONObject

class AlertasFragment : Fragment() {

    private lateinit var binding: FragmentAlertasBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlertasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Acceder a las vistas utilizando binding

        val lat = arguments?.getString("lat")
        val long = arguments?.getString("long")

        binding.searchbtn.setOnClickListener {
            val citytxt = binding.citynamesearch.text.toString()
            getJSONDataByname(citytxt)
        }
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


    private fun setValues(response: JSONObject){

        binding.city.text = response.getString("name")
        val lat = response.getJSONObject("coord").getString("lat")
        val long = response.getJSONObject("coord").getString("lon")
        binding.coordinates.text = "$lat , $long"

        val main = response.getJSONArray("weather").getJSONObject(0).getString("main")
        binding.weather.text= main


        var tempr=response.getJSONObject("main").getString("temp")
        tempr=((((tempr).toFloat()-273.15)).toInt()).toString()
        binding.temp.text="${tempr}°C"


        var mintemp=response.getJSONObject("main").getString("temp_min")
        mintemp=((((mintemp).toFloat()-273.15)).toInt()).toString()
        binding.minTemp.text=mintemp+"°C"

        var maxtemp=response.getJSONObject("main").getString("temp_max")
        maxtemp=((((maxtemp).toFloat()-273.15)).toInt()).toString()
        binding.maxTemp.text=maxtemp+"°C"

        binding.pressure.text=response.getJSONObject("main").getString("pressure")
        binding.humidity.text=response.getJSONObject("main").getString("humidity")+"%"
        binding.wind.text=response.getJSONObject("wind").getString("speed")
        binding.degree.text="Degree : "+response.getJSONObject("wind").getString("deg")+"°"
//        gust.text="Gust : "+response.getJSONObject("wind").getString("gust")
    }
}
