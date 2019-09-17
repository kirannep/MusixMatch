package com.example.musixmatch.view

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musixmatch.network.GetArtistRequest

class FragmentArtistModelFactory(private val clientInterface:GetArtistRequest, private val application:Application):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArtistViewModel(application,clientInterface) as T
    }

}