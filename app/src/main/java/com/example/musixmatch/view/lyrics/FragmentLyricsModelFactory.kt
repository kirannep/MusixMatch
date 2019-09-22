package com.example.musixmatch.view.lyrics

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musixmatch.dependency_injection.application.MyApplication
import com.example.musixmatch.network.GetArtistRequest

class FragmentLyricsModelFactory(private val clientInterface:GetArtistRequest, private val application:Application):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LyricsViewModel(application, clientInterface) as T
    }

}