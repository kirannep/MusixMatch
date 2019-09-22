package com.example.musixmatch.view.track


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.musixmatch.R
import com.example.musixmatch.dependency_injection.component.DaggerAppComponent
import com.example.musixmatch.dependency_injection.network_module.NetworkModule
import com.example.musixmatch.model.track.BaseModelTrack
import com.example.musixmatch.model.track.Track
import com.example.musixmatch.view.lyrics.LyricsFragment
import kotlinx.android.synthetic.main.fragment_track.*
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
        trackviewModel.trackRetrofit()?.observe(this,Observer<BaseModelTrack>{
                t ->
            Log.d("basemodeltrack",t.message.body.track_list[0].track.track_name)
            Log.d("basemodeltrack2",t.message.body.track_list[1].track.track_name)
            trackAdapter(t)
        })

        trackviewModel.showProgress().observe(this,object:Observer<Boolean>{
            override fun onChanged(t: Boolean?) {
                if (t == true){
                    pgbar_track.visibility = View.VISIBLE
                }
                if (t == false){
                    pgbar_track.visibility = View.GONE
                }
            }

        })
    }

    private fun trackAdapter(t:BaseModelTrack){
        val adapter = TrackAdapter(t,object: onTrackClickListener{
            override fun onTrackCliked(track: Track) {
                val fragmentManager = activity?.supportFragmentManager
                val transaction = fragmentManager?.beginTransaction()
                val args = Bundle()
                args.putInt("trackId",track.track_id)
                val lyricsFragment = LyricsFragment()
                lyricsFragment.arguments = args
                transaction?.replace(R.id.fragmentContainer,lyricsFragment)
                    ?.addToBackStack(null)
                    ?.commit()
            }
        })
        trackRecyclerView.layoutManager = LinearLayoutManager(context)
        trackRecyclerView.adapter = adapter
    }

}
