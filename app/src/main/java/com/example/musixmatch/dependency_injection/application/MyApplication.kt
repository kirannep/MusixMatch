package com.example.musixmatch.dependency_injection.application

import android.app.Application
import com.example.musixmatch.dependency_injection.component.DaggerAppComponent
import com.example.musixmatch.dependency_injection.network_module.NetworkModule


class MyApplication:Application() {

    override fun onCreate() {
        super.onCreate()



        DaggerAppComponent.builder()
           .networkModule(NetworkModule(this))
            .build()
            .inject(this)
    }


}