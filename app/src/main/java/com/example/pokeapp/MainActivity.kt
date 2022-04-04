package com.example.pokeapp

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import com.example.pokeapp.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBuscar.setOnClickListener {
            buscarPokemon()
        }
    }

    fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun buscarPokemon(){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val txtFiltro: String = binding.txtBuscar.text.toString().lowercase()
                val call = getRetrofit().create(ApiService::class.java).getDatosPokemon(txtFiltro)
                if (call.isSuccessful){
                    val name: String = call.body()?.name.toString()
                    val type: String = call.body()?.type.toString()
                    val height: String = call.body()?.height.toString()
                    val weight: String = call.body()?.weight.toString()

                    val id: Int? = call.body()?.id?.toInt()

                    binding.txtName.text = name
                    binding.txtType.text = type
                    binding.txtWeight.text = weight
                    binding.txtHeight.text = height


                    Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png").into(binding.imgPokemon)
                }
            } catch(ex: Exception){
            val msn = Toast.makeText(this@MainActivity, "Error de conexion", Toast.LENGTH_LONG)
            msn.setGravity(Gravity.CENTER, 0, 0)
            msn.show()
        }
        }
    }


}