package com.example.pokedex.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.viewHolders.PokeListViewHolder
import com.example.pokedex.R
import com.example.pokedex.models.PokemonList
import com.example.pokedex.models.Result
import com.squareup.picasso.Picasso

class PokeListAdapter(val resultModel: PokemonList): RecyclerView.Adapter<PokeListViewHolder>(){
    var onItemClick:((Result)->Unit)?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokeListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_card,parent,false)
        return PokeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokeListViewHolder, position: Int) {
        val pokemon: Result = resultModel.results[position]
        holder.pokeTitle.text=pokemon.name.toUpperCase().toString()

        var string = resultModel.results[position].url
        var id= string.replace("[^0-9]".toRegex(), "").drop(1)
        Picasso.get()
            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png")
            .resize(100,100)
            .placeholder(R.drawable.poke_ball)//it will show placeholder image when url is not valid.
            .into(holder.pokeImage)

        holder.itemView.setOnClickListener{
            onItemClick?.invoke(pokemon)
        }
    }

    override fun getItemCount(): Int {
        return resultModel.results.size
    }
}


