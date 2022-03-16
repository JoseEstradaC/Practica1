package com.example.practica1.facturas

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.practica1.R
import com.example.practica1.databinding.RvFacturaBinding
import java.lang.String.format
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class FacturasAdapter(
    private var datos: MutableList<Factura>,
) : RecyclerView.Adapter<FacturasAdapter.FacturaContenedor>() {

    override fun getItemCount(): Int = datos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacturaContenedor {
        val inflador = LayoutInflater.from(parent.context)
        val binding = RvFacturaBinding.inflate(inflador, parent, false)

        return FacturaContenedor(binding)
    }

    override fun onBindViewHolder(holder: FacturaContenedor, position: Int) {
        holder.bindFacturas(datos[position])
    }

    inner class FacturaContenedor(private val binding: RvFacturaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindFacturas(factura: Factura) {
            binding.tvEstado.text = factura.descEstado

            if (factura.descEstado == "Pendiente de pago") {
                binding.tvEstado.setTextColor(
                    ContextCompat.getColor(
                        binding.tvEstado.context,
                        R.color.rojo
                    )
                )
            }


            val fecha = LocalDate.parse(
                factura.fecha,
                DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale("es", "ES"))
            )
            val fechaString =
                fecha.format(DateTimeFormatter.ofPattern("dd MMM yyyy", Locale("es", "ES")))

            binding.tvFecha.text = fechaString
            binding.tvPrecio.text = format(Locale("es", "ES"), "%.2f €", factura.importeOrdenacion)

        }
    }
}