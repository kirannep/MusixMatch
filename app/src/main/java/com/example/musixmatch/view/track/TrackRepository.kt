package com.example.musixmatch.view.track

import android.app.Application
import com.example.musixmatch.common.Constants
import com.example.musixmatch.database.ArtistDatabase
import com.example.musixmatch.model.artist.BaseModel
import com.example.musixmatch.model.track.BaseModelTrack
import com.example.musixmatch.network.GetArtistRequest
import io.reactivex.Observable
import javax.inject.Inject

class TrackRepository @Inject constructor(private val application: Application, val clientInterface: GetArtistRequest) {

    lateinit var callrequest: Observable<BaseModelTrack>
    val dbRequest = ArtistDatabase.getInstance(application).artistDAO()

    fun trackFromRetrofit(artistname:String):Observable<BaseModelTrack>{
        callrequest = clientInterface.getTrackOfArtist(artistname, Constants.API_KEY)
        return callrequest
    }

//    fun insertArtistInDB(t: List<Artist>):Completable{
////
//        return dbRequest.insertArtist(t)
//    }
//
//
//
//    fun getArtistFromDB():Observable<List<Artist>>{
////        dbRequest = ArtistDatabase.getInstance(application).artistDAO()
//        return dbRequest.getArtist()
//    }

}