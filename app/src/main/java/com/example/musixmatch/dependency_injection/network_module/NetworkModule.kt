package com.example.musixmatch.dependency_injection.network_module

import android.app.Application
import com.example.musixmatch.common.Constants
import com.example.musixmatch.network.GetArtistRequest
import com.example.musixmatch.view.FragmentArtistModelFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideInterceptor(): HttpLoggingInterceptor {
      return HttpLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun provideOKHTTPClient(loggingInterceptor: HttpLoggingInterceptor):OkHttpClient{
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideClientInterface(retrofit: Retrofit) = retrofit.create(GetArtistRequest::class.java)

    @Provides
    @Singleton
    fun provideApplicationContext():Application = application

    @Provides
    @Singleton
    fun provideCakeViewModelFactory(clientInterface:GetArtistRequest,application: Application): FragmentArtistModelFactory {
        return FragmentArtistModelFactory(clientInterface,application)
    }
}