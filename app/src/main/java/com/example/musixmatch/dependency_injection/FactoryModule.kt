package com.example.musixmatch.dependency_injection

import android.app.Application
import com.example.musixmatch.network.GetArtistRequest
import com.example.musixmatch.view.artist.ArtistRepository
import com.example.musixmatch.view.lyrics.LyricsRepository
import com.example.musixmatch.view.track.TrackRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FactoryModule {

    @Provides
    @Singleton
    fun provideArtistRepository(clientInterface: GetArtistRequest, application: Application): ArtistRepository {
        return ArtistRepository(application,clientInterface)
    }

    @Provides
    @Singleton
    fun provideTrackRepository(clientInterface: GetArtistRequest,application: Application):TrackRepository{
        return TrackRepository(application,clientInterface)
    }

    @Provides
    @Singleton
    fun provideLyricsRepository(clientInterface: GetArtistRequest,application: Application):LyricsRepository{
        return LyricsRepository(application,clientInterface)
    }
}