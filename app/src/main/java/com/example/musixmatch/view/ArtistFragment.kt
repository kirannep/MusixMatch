package com.example.musixmatch.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.musixmatch.R
import com.example.musixmatch.dependency_injection.application.MyApplication
import com.example.musixmatch.dependency_injection.component.DaggerAppComponent
import com.example.musixmatch.dependency_injection.network_module.NetworkModule
import com.example.musixmatch.model.BaseModel
import com.example.musixmatch.network.GetArtistRequest
import javax.inject.Inject

class ArtistFragment : Fragment() {

    @Inject
    lateinit var fragmentArtistModelFactory: FragmentArtistModelFactory
    private lateinit var viewModel: ArtistViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_artist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerAppComponent.builder()
            .networkModule(NetworkModule(activity!!.application))
            .build()
            .inject(this)

        viewModel = ViewModelProviders.of(this,fragmentArtistModelFactory).get(ArtistViewModel::class.java)
        viewModel.getArtistFromRetrofit()
        viewModel.artistRetrofit()?.observe(this,
            Observer<BaseModel> {
                    t ->
                Log.i("resultFromRetrofit", ""+t.message.body.artist_list[0].artist.artist_name)
                //cakeAdapterData(t)
            })
    }
}
