package com.example.proyecto_integrador.Activitis

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.example.proyecto_integrador.Activities.InformacionFragment
import com.example.proyecto_integrador.Domains.InformacionDomain
import com.example.proyecto_integrador.R
import com.example.proyecto_integrador.data.Detalle


class DetalleFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val detalle = it.getParcelable<Detalle>("info")
            detalle?.let {
                val titleTxt: TextView = requireView().findViewById(R.id.titleTxt)
                val imageView: ImageView = requireView().findViewById(R.id.imageView)
                val detailTxt: TextView = requireView().findViewById(R.id.detailTxt)

                titleTxt.text = detalle.name
                detalle.picPath?.let { it1 -> imageView.setImageResource(it1) }
                detailTxt.text = detalle.other
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detalle, container, false)
        setDetalle(view)
        return view
    }

    private fun setDetalle(view: View) {
        val backBtn: ImageView = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
            transaction.replace(R.id.frameContainer, InformacionFragment())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
