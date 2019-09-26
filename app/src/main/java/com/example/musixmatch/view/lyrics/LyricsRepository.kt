package com.example.musixmatch.view.lyrics

import android.app.Application
import com.example.musixmatch.common.Constants
import com.example.musixmatch.database.ArtistDatabase
import com.example.musixmatch.model.lyrics.BaseModelLyrics
import com.example.musixmatch.network.GetArtistRequest
import io.reactivex.Observable
import javax.inject.Inject

class LyricsRepository @Inject constructor(private val application: Application, val clientInterface: GetArtistRequest) {
    lateinit var callrequest: Observable<BaseModelLyrics>
    val dbRequest = ArtistDatabase.getInstance(application).artistDAO()

    fun trackFromRetrofit(trackId:Int):Observable<BaseModelLyrics>{
        callrequest = clientInterface.getLyricsOfTrack(trackId, Constants.API_KEY)
        return callrequest
    }
}