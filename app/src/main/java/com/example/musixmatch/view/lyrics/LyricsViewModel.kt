package com.example.musixmatch.view.lyrics

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musixmatch.common.Constants
import com.example.musixmatch.dependency_injection.application.MyApplication
import com.example.musixmatch.model.lyrics.BaseModelLyrics
import com.example.musixmatch.network.GetArtistRequest
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LyricsViewModel @Inject constructor(application: Application, val clientInterface:GetArtistRequest):ViewModel() {

    val lyricsObserver = LyricsObserver()
    val compositeDisposable = CompositeDisposable()
    private val lyrics:MutableLiveData<BaseModelLyrics> = MutableLiveData()
    private val showProgressBar:MutableLiveData<Boolean> = MutableLiveData()
    var lyricsRepository:LyricsRepository = LyricsRepository(application,clientInterface)

    fun getLyricsFromRetrofit(id:Int){
        showProgressBar.value = true
         lyricsRepository.trackFromRetrofit(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(lyricsObserver)
    }

    private fun LyricsObserver():Observer<BaseModelLyrics>{
        return object: Observer<BaseModelLyrics>{
            override fun onComplete() {
                Log.d("emitted", "all items emitted")
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: BaseModelLyrics) {
                lyrics?.value = t
                showProgressBar.value = false
            }

            override fun onError(e: Throwable) {
                Log.d("lyricsError",""+e.message)
            }
        }
    }

    fun getLyrics():MutableLiveData<BaseModelLyrics>?{
        return lyrics
    }

    fun showprogress():MutableLiveData<Boolean>{
        return showProgressBar
    }

}