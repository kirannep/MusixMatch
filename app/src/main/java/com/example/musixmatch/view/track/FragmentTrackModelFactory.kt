package com.example.musixmatch.view.track

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musixmatch.network.GetArtistRequest

class FragmentTrackModelFactory(private val clientInterface:GetArtistRequest, private val application:Application):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrackViewModel(application, clientInterface) as T
    }

}