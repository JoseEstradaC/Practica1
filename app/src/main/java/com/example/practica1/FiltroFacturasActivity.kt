package com.example.practica1

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.practica1.databinding.ActivityFiltroFacturasBinding


class FiltroFacturasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFiltroFacturasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiltroFacturasBinding.inflate(layoutInflater)
        binding.BtnCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
        binding.FechaDesde.setOnClickListener { showDatePickerDialog(binding.FechaDesde) }
        binding.FechaHasta.setOnClickListener { showDatePickerDialog(binding.FechaHasta) }

        setContentView(binding.root)
    }

    private fun showDatePickerDialog(fechaDesde: TextView) {
        val datePicker =
            DatePickerFragment { year, month, day ->
                onDateSelected(day,
                    month + 1,
                    year,
                    fechaDesde)
            }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int, fechaDesde: TextView) {
        fechaDesde.text = "$day/$month/$year"
    }
}