package com.example.practica1.inicio

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica1.FiltroFacturasActivity
import com.example.practica1.api.APIService
import com.example.practica1.facturas.Factura
import com.example.practica1.facturas.FacturasAdapter
import com.example.practica1.facturas.FacturasResponse
import com.example.practica1.databinding.FragmentInicioBinding
import com.example.practica1.facturas.FiltroFacturas
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate

class InicioFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null
    private val binding get() = _binding!!
    private lateinit var apiInterface: Call<FacturasResponse>;
    private var filtro: FiltroFacturas = FiltroFacturas();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://viewnextandroid.mocklab.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiInterface = APIService.create().getMovies()

        apiInterface.enqueue(object : Callback<FacturasResponse> {
            override fun onResponse(
                call: Call<FacturasResponse?>,
                response: Response<FacturasResponse?>,
            ) {
                val respuesta = response.body()
                val facturas: MutableList<Factura> = respuesta?.facturas ?: mutableListOf<Factura>()

                //filtro = FiltroFacturas(15.0, 30.0, LocalDate.of(2019, 1, 1), LocalDate.MAX)

                //val adaptador = FacturasAdapter(filtro.filtrar(facturas))
                val adaptador = FacturasAdapter(facturas)
                binding.rvFacturas.adapter = adaptador
                binding.rvFacturas.layoutManager = LinearLayoutManager(activity)
                val dividerItemDecoration =
                    DividerItemDecoration(binding.rvFacturas.context, VERTICAL)
                binding.rvFacturas.addItemDecoration(dividerItemDecoration);
                binding.rvFacturas.setHasFixedSize(true)
            }

            override fun onFailure(call: Call<FacturasResponse>?, t: Throwable?) {
                Log.e("Facturas", t.toString())
            }

        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentInicioBinding.inflate(inflater, container, false)

        var resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                }
            }

        binding.BtnFilter.setOnClickListener {
            val intent = Intent(this.context, FiltroFacturasActivity::class.java)
            resultLauncher.launch(intent)
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        apiInterface.cancel();
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = InicioFragment()
    }
}