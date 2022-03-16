package com.example.practica1.api

import com.example.practica1.facturas.FacturasResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIService {

    @GET("facturas")
    fun getMovies(): Call<FacturasResponse>

    companion object {

        private var BASE_URL = "https://viewnextandroid.mocklab.io/"

        fun create(): APIService {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(APIService::class.java)

        }
    }
}