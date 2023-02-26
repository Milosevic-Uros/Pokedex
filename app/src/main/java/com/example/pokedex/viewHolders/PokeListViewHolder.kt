package com.example.pokedex.viewHolders

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.DetailActivity
import com.example.pokedex.R
import com.example.pokedex.models.Pokemon
import com.squareup.picasso.Picasso
import java.nio.InvalidMarkException
import kotlin.math.log

class PokeListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    var pokeTitle: TextView = itemView.findViewById(R.id.pokeTitle)
    var pokeImage:ImageView=itemView.findViewById(R.id.pokeImage)

    fun bindView(Result:com.example.pokedex.models.Result){
        pokeTitle.text=Result.name
        var string = Result.url
        var id=string.filter { it.isLetter() }

        pokeImage.setImageURI(Uri.parse("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/dream-world/${id}.svg"))
    }
}

