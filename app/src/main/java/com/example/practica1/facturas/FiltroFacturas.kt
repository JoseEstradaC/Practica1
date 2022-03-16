package com.example.practica1.facturas

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class FiltroFacturas(
    var importeMin: Double = Double.MIN_VALUE,
    var importeMax: Double = Double.MAX_VALUE,
    var fechaMin: LocalDate = LocalDate.MIN,
    var fechaMax: LocalDate = LocalDate.MAX,
    private var estados: MutableList<String> = mutableListOf<String>()
) {
    fun addEstado(estado: String)
    {
        if (estado !in estados)
            estados.add(estado)
        return
    }
    fun delEstado(estado: String)
    {
        if (estado in estados)
            estados.remove(estado)
        return
    }

    fun filtrar(listaFacturas: MutableList<Factura>): MutableList<Factura> {
        return (listaFacturas.filter {
            val date = LocalDate.parse(
                it.fecha,
                DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale("es", "ES"))
            )
            if (estados.isEmpty())
                !(date.isBefore(fechaMin) || date.isAfter(fechaMax)) && it.importeOrdenacion in importeMin..importeMax
            else
                !(date.isBefore(fechaMin) || date.isAfter(fechaMax)) && it.importeOrdenacion in importeMin..importeMax && it.descEstado in estados
        }) as MutableList<Factura>

    }

}