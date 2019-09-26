package com.example.musixmatch.view.artist

import android.app.Application
import com.example.musixmatch.common.Constants
import com.example.musixmatch.database.ArtistDatabase
import com.example.musixmatch.database.ArtistSearchAccess
import com.example.musixmatch.model.artist.Artist
import com.example.musixmatch.model.artist.Artist_list
import com.example.musixmatch.model.artist.BaseModel
import com.example.musixmatch.network.GetArtistRequest
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ArtistRepository @Inject constructor(private val application: Application, val clientInterface: GetArtistRequest) {


    lateinit var callrequest:Observable<BaseModel>
//    lateinit var dbRequest : ArtistSearchAccess
    val dbRequest = ArtistDatabase.getInstance(application).artistDAO()


    fun artistFromRetrofit(artistname:String):Observable<BaseModel>{
        callrequest = clientInterface.getartist(artistname,Constants.API_KEY)
        return callrequest
    }

    fun insertArtistInDB(t: List<Artist>):Completable{
//        val list = mutableListOf<Artist>()
//        for (i in t){
//            list.add(i.artist)
//        }
        return dbRequest.insertArtist(t)
    }

    fun getIndividualFromDB(artistname: String):Observable<List<Artist>>{
        return dbRequest.getIndividualArtist(artistname)
    }

    fun getArtistFromDB():Observable<List<Artist>>{
//        dbRequest = ArtistDatabase.getInstance(application).artistDAO()
        return dbRequest.getArtist()
    }
}