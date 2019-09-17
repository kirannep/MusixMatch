package com.example.musixmatch.view

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musixmatch.common.Constants
import com.example.musixmatch.model.BaseModel
import com.example.musixmatch.network.GetArtistRequest
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ArtistViewModel @Inject constructor(application: Application, val clientInterface: GetArtistRequest):ViewModel() {
    val artistObserver=ArtistObserver()
    val compositeDisposable = CompositeDisposable()
    private val artist: MutableLiveData<BaseModel>? = MutableLiveData()

    fun getArtistFromRetrofit(){
        val call: Observable<BaseModel> = clientInterface.getartist(Constants.API_KEY)
        call
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(artistObserver)
    }

    private fun ArtistObserver():Observer<BaseModel>{
        return object: Observer<BaseModel>{
            override fun onComplete() {
                Log.d("emitted","all items emitted")
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: BaseModel) {
                artist?.value = t
            }

            override fun onError(e: Throwable) {
                Log.d("errormsgRetrofit",e.message)
            }
        }
    }

    fun artistRetrofit():MutableLiveData<BaseModel>?{
        return artist
    }

}