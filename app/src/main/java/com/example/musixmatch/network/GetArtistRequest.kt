package com.example.musixmatch.network

import com.example.musixmatch.model.artist.BaseModel
import com.example.musixmatch.model.track.BaseModelTrack
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GetArtistRequest {
    @GET("artist.search?q_artist=ed&page_size=5")
    fun getartist(@Query("apikey") apikey:String) :Observable<BaseModel>

//    @GET("track.search?q_artist=prodigy&page_size=5&page=1&s_track_rating=desc")
//    fun getTrackOfArtist(@Query("apikey") apikey:String) :Observable<BaseModelTrack>

    @GET("track.search?&page_size=5&page=1&s_track_rating=desc")
    fun getTrackOfArtist(@Query("q_artist") q_artist:String,@Query("apikey") apikey:String) :Observable<BaseModelTrack>



}