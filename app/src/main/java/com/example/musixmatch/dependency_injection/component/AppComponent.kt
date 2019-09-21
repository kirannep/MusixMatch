package com.example.musixmatch.dependency_injection.component

import com.example.musixmatch.dependency_injection.application.MyApplication
import com.example.musixmatch.dependency_injection.network_module.NetworkModule
import com.example.musixmatch.view.artist.ArtistFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(NetworkModule::class))
interface AppComponent {
    fun inject(myApplication: MyApplication)
    fun inject(artistFragment: ArtistFragment)
}