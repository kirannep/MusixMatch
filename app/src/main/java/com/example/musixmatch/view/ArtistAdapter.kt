package com.example.musixmatch.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musixmatch.R
import com.example.musixmatch.model.Artist
import com.example.musixmatch.model.BaseModel
import kotlinx.android.synthetic.main.cardview.view.*

class ArtistAdapter(private val artist:BaseModel):RecyclerView.Adapter<ArtistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview,parent,false))
    }

    override fun getItemCount(): Int {
        return artist.message.body.artist_list.size
        //return artist.size
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.artist_name.text = artist.message.body.artist_list[position].artist.artist_name
        holder.artist_rating.text = artist.message.body.artist_list[position].artist.artist_rating.toString()
        holder.artist_country.text = artist.message.body.artist_list[position].artist.artist_country

//        holder.artist_name.text = artist[position].artist_name
//        holder.artist_rating.text = artist[position].artist_rating.toString()
//        holder.artist_country.text = artist[position].artist_country
    }

}

class ArtistViewHolder(view: View):RecyclerView.ViewHolder(view){
    val artist_name = view.tv_name
    val artist_rating = view.tv_rating
    val artist_country = view.tv_country
}