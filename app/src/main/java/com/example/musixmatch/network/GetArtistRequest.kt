package com.example.musixmatch.network

import com.example.musixmatch.model.artist.BaseModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GetArtistRequest {
    @GET("artist.search?q_artist=prodigy&page_size=5")
    fun getartist(@Query("apikey") apikey:String) :Observable<BaseModel>
}