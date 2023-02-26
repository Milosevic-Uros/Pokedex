package com.example.pokedex

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.pokedex.api.ApiService
import com.example.pokedex.api.ServiceGenerator
import com.example.pokedex.models.Pokemon
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_detail)

        var pokemonImage:ImageView?= findViewById<ImageView>(R.id.pokemonImage)
        var pokemonType1:TextView? = findViewById<Button>(R.id.pokemonType1)
        var pokemontype2:TextView?= findViewById<Button>(R.id.pokemonType2)
        var pokemonName:TextView?= findViewById<TextView>(R.id.pokemonName)
        var pokemonWeight:TextView?= findViewById<TextView>(R.id.pokemonWeight)
        var pokemonHeight:TextView?= findViewById<TextView>(R.id.pokemonHeight)
        var pokemonHp:TextView = findViewById<TextView>(R.id.hpstat)
        var pokemonAtk:TextView = findViewById<TextView>(R.id.atkstat)
        var pokemonDef:TextView = findViewById<TextView>(R.id.defstat)
        var pokemonSpAtk:TextView = findViewById<TextView>(R.id.spatkstat)
        var pokemonSpDef:TextView = findViewById<TextView>(R.id.spdefstat)
        var pokemonSpd:TextView = findViewById<TextView>(R.id.spdstat)

        val service = ServiceGenerator.buildService(ApiService::class.java)

        val imageButton: ImageButton? =findViewById(R.id.arrow_left)
        imageButton?.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }

        val pokemon:String?
        val i=intent.getStringExtra("pokemonName")
        pokemon=i
        val pokemonData = pokemon?.let { service.getPokemon(it) }

        pokemonData?.enqueue(object : Callback<Pokemon?> {
            override fun onResponse(call: Call<Pokemon?>, response: Response<Pokemon?>) {
                val responseBody = response.body()
                try {
                    pokemontype2?.text=responseBody?.types?.get(1)?.type?.name.toString()
                }catch (ex:java.lang.Exception){
                    pokemontype2?.text=null
                    pokemontype2?.visibility= View.GONE
                }finally{
                    pokemonName?.text=responseBody?.name?.toUpperCase()
                    val height=responseBody?.height?.toDouble()?.times(10).toString()
                    pokemonHeight?.text= height+" cm"
                    val weight=responseBody?.weight?.toDouble()?.div(10).toString()
                    pokemonWeight?.text=weight + " kg"
                    pokemonType1?.text=responseBody?.types?.get(0)?.type?.name.toString()

                    pokemonHp.text= responseBody?.stats?.get(0)?.baseStat.toString()
                    pokemonAtk.text= responseBody?.stats?.get(1)?.baseStat.toString()
                    pokemonDef.text= responseBody?.stats?.get(2)?.baseStat.toString()
                    pokemonSpAtk.text= responseBody?.stats?.get(3)?.baseStat.toString()
                    pokemonSpDef.text= responseBody?.stats?.get(4)?.baseStat.toString()
                    pokemonSpd.text= responseBody?.stats?.get(5)?.baseStat.toString()
                }
                Picasso.get().load(responseBody?.sprites?.other?.officialArtwork?.frontDefault?.
                toUri()).
                into(pokemonImage);
            }

            override fun onFailure(call: Call<Pokemon?>, t: Throwable) {
                Log.d("DetailActivity","onFailure: "+t.message)
            }
        })


    }
}