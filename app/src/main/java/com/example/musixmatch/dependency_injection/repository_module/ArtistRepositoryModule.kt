package com.example.musixmatch.dependency_injection.repository_module

import android.app.Application
import com.example.musixmatch.network.GetArtistRequest
import com.example.musixmatch.view.artist.ArtistRepository
import com.example.musixmatch.view.artist.FragmentArtistModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ArtistRepositoryModule {

    @Provides
    @Singleton
    fun provideArtistViewModelFactory(artistRepository: ArtistRepository):FragmentArtistModelFactory{
        return FragmentArtistModelFactory(artistRepository)
    }
}