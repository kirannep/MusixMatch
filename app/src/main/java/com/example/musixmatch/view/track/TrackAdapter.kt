package com.example.musixmatch.view.track

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musixmatch.R
import com.example.musixmatch.model.track.BaseModelTrack
import com.example.musixmatch.model.track.Track
import kotlinx.android.synthetic.main.cardview_track.view.*

class TrackAdapter(private val trackmodel:BaseModelTrack,private val listener:onTrackClickListener):RecyclerView.Adapter<TrackViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview_track,parent,false))
    }

    override fun getItemCount(): Int {
        return trackmodel.message.body.track_list.size
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.trackName.text = trackmodel.message.body.track_list[position].track.track_name
        holder.albumName.text = trackmodel.message.body.track_list[position].track.album_name
        holder.bind(trackmodel.message.body.track_list[position].track,listener)
    }

}

class TrackViewHolder(view: View):RecyclerView.ViewHolder(view){
    val trackName = view.tv_trackName
    val albumName = view.tv_albumName

    fun bind(track: Track,listener: onTrackClickListener){
        itemView.setOnClickListener {
            listener.onTrackCliked(track)
        }
    }
}

interface onTrackClickListener{
    fun onTrackCliked(track:Track)
}