package com.example.pokedex

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.adapters.PokeListAdapter
import com.example.pokedex.api.ApiService
import com.example.pokedex.api.ServiceGenerator
import com.example.pokedex.models.PokemonList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        val mediaPlayer = MediaPlayer()
        val recyclerView = findViewById<RecyclerView>(R.id.myRecyclerView)
        val service = ServiceGenerator.buildService(ApiService::class.java)
        val searchBar = findViewById<androidx.appcompat.widget.SearchView>(R.id.pokeSearch)
        val call = service.getPokemonList()
        val play:ImageButton = findViewById(R.id.playButton)
        val pause:ImageButton = findViewById(R.id.pauseButton)
        val urlPrefix = "android.resource://com.example.pokedex/"
        pause.visibility=View.GONE


        play.setOnClickListener {
            playSong(this, mediaPlayer, play, pause,urlPrefix,R.raw.pesma)
        }
        pause.setOnClickListener {
            pauseSong(this,mediaPlayer,play,pause)
        }

        call.enqueue(object: Callback<PokemonList>{
            override fun onResponse(
                call: Call<PokemonList>,
                response: Response<PokemonList>
            ) {
                if (response.isSuccessful) {
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                    val pokeAdapter = PokeListAdapter(response.body()!!)
                    recyclerView.adapter = pokeAdapter
                    pokeAdapter.onItemClick = {
                        detailActivity(it.name)
                    }
                }
            }
            override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                t.printStackTrace()
                Log.e("error",t.message.toString())
            }
        })

        /*Search Code here*/
        searchBar.setOnQueryTextListener(object:androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                call.cancel()
                call.clone().enqueue(object: Callback<PokemonList>{
                    override fun onResponse(
                        call: Call<PokemonList>,
                        response: Response<PokemonList>
                    ) {
                        if (response.isSuccessful) {
                            val list =response.body()?.results?.filter { x->x.name.contains(searchBar.query.toString().toLowerCase()) }
                            if (list != null) {
                                response.body()?.results=list
                            }
                            var pokeAdapter = PokeListAdapter(response.body()!!)
                            recyclerView.adapter = pokeAdapter
                            pokeAdapter.onItemClick = {
                                detailActivity(it.name)
                            }
                        }
                    }

                    override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                        t.printStackTrace()
                        Log.e("error",t.message.toString())
                    }
                })
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                call.cancel()
                call.clone().enqueue(object: Callback<PokemonList>{
                    override fun onResponse(
                        call: Call<PokemonList>,
                        response: Response<PokemonList>
                    ) {
                        if(response.isSuccessful){
                            var list =response.body()?.results?.filter { x->x.name.contains(searchBar.query.toString().toLowerCase()) }
                            if (list != null) {
                                response?.body()?.results=list
                            }
                            val pokeAdapter = PokeListAdapter(response.body()!!)
                            recyclerView.adapter = pokeAdapter
                            pokeAdapter.onItemClick = {
                                detailActivity(it.name)
                            }
                        }
                    }

                    override fun onFailure(call: Call<PokemonList>, t: Throwable) {
                        t.printStackTrace()
                        Log.e("error",t.message.toString())
                    }
                })
                return true
            }
        })
    }



    fun detailActivity(pokemonName:String){
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("pokemonName",pokemonName)
        startActivity(intent)
    }

}

fun playSong(context: Context, mediaPlayer: MediaPlayer?, playButton: View, pauseButton: View, urlPrefix: String="android.resource://com.example.pokedex/", songId: Int = R.raw.pesma) {
    try {
        mediaPlayer!!.reset() // reset the MediaPlayer object before setting a new data source
        mediaPlayer!!.setDataSource(context, Uri.parse(urlPrefix + songId))
        mediaPlayer!!.prepare()
        mediaPlayer!!.start()
        Toast.makeText(context, "Playing pokemon theme song!", Toast.LENGTH_SHORT).show()
        playButton.visibility = View.GONE
        pauseButton.visibility = View.VISIBLE
    } catch (ex: java.lang.Exception) {
        Toast.makeText(context, "An unexpected error has occurred!", Toast.LENGTH_SHORT).show()
    }
}
fun pauseSong(context: Context,mediaPlayer: MediaPlayer?, playButton: View,pauseButton: View) {
    if (mediaPlayer!!.isPlaying) {
        mediaPlayer!!.stop()
        pauseButton.visibility = View.GONE
        playButton.visibility = View.VISIBLE
        Toast.makeText(context, "Playing theme song has stopped!", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "Cant be stopped", Toast.LENGTH_SHORT).show()
    }
}