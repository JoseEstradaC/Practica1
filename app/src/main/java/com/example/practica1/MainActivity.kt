package com.example.practica1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.practica1.inicio.InicioFragment
import com.example.practica1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        openFragment(InicioFragment.newInstance())
        bottomNavigationListener()
    }

    private fun bottomNavigationListener() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navInicio -> {
                    val inicioFragment = InicioFragment.newInstance()
                    openFragment(inicioFragment)
                    return@setOnItemSelectedListener true
                }
                R.id.navConsumo -> {
                    val ultimaHoraFragment = ConsumoFragment.newInstance()
                    openFragment(ultimaHoraFragment)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}