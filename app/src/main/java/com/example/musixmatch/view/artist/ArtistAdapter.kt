package com.example.musixmatch.view.artist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musixmatch.R
import com.example.musixmatch.model.artist.Artist
import com.example.musixmatch.model.artist.Artist_list
import kotlinx.android.synthetic.main.cardview.view.*

class ArtistAdapter(private val artist: List<Artist_list>?, private val listener: onClickArtistListener):RecyclerView.Adapter<ArtistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.cardview,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return artist.size
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.artist_name.text = artist[position].artist.artist_name
        holder.artist_rating.text = artist[position].artist.artist_rating.toString()
        holder.artist_country.text = artist[position].artist.artist_country
        holder.bind(artist[position].artist,listener)
    }

}

class ArtistViewHolder(view: View):RecyclerView.ViewHolder(view){
    val artist_name = view.tv_name
    val artist_rating = view.tv_rating
    val artist_country = view.tv_country

    fun bind(artist: Artist, listener: onClickArtistListener){
        itemView.setOnClickListener{
            listener.onClickedArtist(artist)
        }
    }
}

interface onClickArtistListener{
    fun onClickedArtist(artist: Artist)
}

