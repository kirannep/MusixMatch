package com.example.musixmatch.view.track

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.musixmatch.common.Constants
import com.example.musixmatch.model.track.BaseModelTrack
import com.example.musixmatch.network.GetArtistRequest
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrackViewModel @Inject constructor(application: Application, val clientInterface:GetArtistRequest):ViewModel() {

    val trackObserver = TrackObserver()
    val compositeDisposable = CompositeDisposable()
    private val track: MutableLiveData<BaseModelTrack>? = MutableLiveData()

    fun getTrackFromRetrofit(artistName:String) {
        val call: Observable<BaseModelTrack> = clientInterface.getTrackOfArtist(artistName,Constants.API_KEY)
        Log.d("call",call.toString())
        call
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(trackObserver)
    }

    private fun TrackObserver(): io.reactivex.Observer<BaseModelTrack> {
        return object : io.reactivex.Observer<BaseModelTrack> {
            override fun onComplete() {
                Log.d("emitted", "all items emitted")
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: BaseModelTrack) {
                track?.value = t



            }

            override fun onError(e: Throwable) {
                Log.d("trackError", e.message)
            }
        }
    }

    fun trackRetrofit():MutableLiveData<BaseModelTrack>?{
        return track
    }

}