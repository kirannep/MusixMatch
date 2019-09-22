package com.example.musixmatch.view.lyrics


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.musixmatch.R
import com.example.musixmatch.dependency_injection.component.DaggerAppComponent
import com.example.musixmatch.dependency_injection.network_module.NetworkModule
import com.example.musixmatch.model.lyrics.BaseModelLyrics
import kotlinx.android.synthetic.main.fragment_lyrics.*
import javax.inject.Inject


class LyricsFragment : Fragment() {

    @Inject
    lateinit var fragmentLyricsModelFactory: FragmentLyricsModelFactory
    private lateinit var lyricsviewModel: LyricsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lyrics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trackIdFromBundle = arguments?.getInt("trackId")

        DaggerAppComponent.builder()
            .networkModule(NetworkModule(activity!!.application))
            .build()
            .inject(this)

        lyricsviewModel = ViewModelProviders.of(this,fragmentLyricsModelFactory).get(LyricsViewModel::class.java)
        if (trackIdFromBundle != null) {
            lyricsviewModel.getLyricsFromRetrofit(trackIdFromBundle)
        }
        lyricsviewModel.getLyrics()?.observe(this,Observer<BaseModelLyrics>{
            t ->
            Log.d("itemFromLyrics",t.message.body.lyrics.lyrics_body)
            tv_lyrics.text = t.message.body.lyrics.lyrics_body
        })

        lyricsviewModel.showprogress().observe(this,object: Observer<Boolean>{
            override fun onChanged(t: Boolean?) {
                if (t == true){
                    pgbar_lyrics.visibility = View.VISIBLE
                }
                if (t == false){
                    pgbar_lyrics.visibility = View.GONE
                }
            }

        })
    }

}
