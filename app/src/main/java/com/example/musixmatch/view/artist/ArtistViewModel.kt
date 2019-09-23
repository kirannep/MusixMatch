package com.example.musixmatch.view.artist

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.musixmatch.common.Constants
import com.example.musixmatch.database.ArtistDatabase
import com.example.musixmatch.model.artist.Artist_list
import com.example.musixmatch.model.artist.BaseModel
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
    private val artist: MutableLiveData<List<Artist_list>>? = MutableLiveData()
    val artistDBrequest = ArtistDatabase.getInstance(application).artistDAO()
    var showSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val getArtistRequest:Observable<List<Artist_list>> = artistDBrequest.getArtist()
    val artistRoomObserver = ArtistRoomObserver()
    private val artistdb:MutableLiveData<List<Artist_list>>? = MutableLiveData()
    var showProgressBar:MutableLiveData<Boolean> = MutableLiveData()

    //retrofit
    fun getArtistFromRetrofit(artistname:String){
        showProgressBar.value = true
        val call: Observable<BaseModel> = clientInterface.getartist(artistname,Constants.API_KEY)
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
                artist?.value = t.message.body.artist_list
                insertArtistinDB(t.message.body.artist_list)
                for (i in 0..t.message.body.artist_list.size-1){
//                    insertArtistinDB(t.message.body.artist_list.get(i).artist)

                }
                    showProgressBar.value = false
            }

            override fun onError(e: Throwable) {
                Log.d("errormsgRetrofit","Something went wrong")
            }
        }
    }

    fun artistRetrofit(): MutableLiveData<List<Artist_list>>? {
        return artist
    }

    //database
    fun insertArtistinDB(t: List<Artist_list>){

            artistDBrequest.insertArtist(t)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({showSuccess.value = true},{
                    Log.i("ViewModel error",it.message)
                    showSuccess.value=false})
    }

    //get data from database
    fun getArtistFromDB(){
        getArtistRequest
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(artistRoomObserver)
    }

    private fun ArtistRoomObserver():Observer<List<Artist_list>>{
        return object: Observer<List<Artist_list>>{
            override fun onComplete() {
                Log.d("emittedFromDB","all items emitted")
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onNext(t: List<Artist_list>) {
                artistdb?.value = t
            }

            override fun onError(e: Throwable) {
                Log.d("errorFromDB",e.message)
            }
        }
    }

    fun artistFromDB():MutableLiveData<List<Artist_list>>?{
        return artistdb
    }

    fun showProgress():MutableLiveData<Boolean>{
        return showProgressBar
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("Destroy ", "ViewModel Destroyed")
    }
    fun onDestroy() {
        compositeDisposable.clear()
    }
}