package com.example.musixmatch.view.artist

import android.app.Application
import com.example.musixmatch.common.Constants
import com.example.musixmatch.model.artist.Artist
import com.example.musixmatch.model.artist.BaseModel
import com.example.musixmatch.network.GetArtistRequest
import io.reactivex.Observable
import javax.inject.Inject

class ArtistRepository @Inject constructor(val clientInterface:GetArtistRequest,application: Application):Application(){

    lateinit var call : Observable<BaseModel>

    fun ArtistFromRetrofit(artistname:String):Observable<BaseModel>{
        call = clientInterface.getartist(artistname,Constants.API_KEY)
        return call
    }



}