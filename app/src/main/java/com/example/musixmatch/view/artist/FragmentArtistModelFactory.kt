package com.example.musixmatch.view.artist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.musixmatch.dependency_injection.repository_module.ArtistRepositoryModule
import com.example.musixmatch.network.GetArtistRequest
import javax.inject.Inject

class FragmentArtistModelFactory (private val artistRepository: ArtistRepository) :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArtistViewModel(artistRepository) as T
    }

}