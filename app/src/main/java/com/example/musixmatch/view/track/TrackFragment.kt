package com.example.musixmatch.view.track


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders

import com.example.musixmatch.R
import com.example.musixmatch.dependency_injection.component.DaggerAppComponent
import com.example.musixmatch.dependency_injection.network_module.NetworkModule
import javax.inject.Inject


class TrackFragment : Fragment() {
    @Inject
    lateinit var fragmentTrackModelFactory: FragmentTrackModelFactory
    private lateinit var trackviewModel: TrackViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_track, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val artistNameFromBundle = arguments?.getString("artistName")
            Log.d("artistNameFromBundle", artistNameFromBundle.toString())

//        val artistIdFromBundle = arguments?.getInt("artistId")
        DaggerAppComponent.builder()
            .networkModule(NetworkModule(activity!!.application))
            .build()
            .inject(this)

        trackviewModel = ViewModelProviders.of(this,fragmentTrackModelFactory).get(TrackViewModel::class.java)
        if (artistNameFromBundle != null) {
            trackviewModel.getTrackFromRetrofit(artistNameFromBundle)
        }
//        viewModel.trackRetrofit()?.observe(this,object:Observer<BaseModelTrack>{
//            override fun onChanged(t: BaseModelTrack?) {
//                Log.d("tracksuccess",t)
//            }
//
//        })
    }




}
