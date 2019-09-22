package com.example.musixmatch.view.artist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.musixmatch.R
import com.example.musixmatch.dependency_injection.component.DaggerAppComponent
import com.example.musixmatch.dependency_injection.network_module.NetworkModule
import com.example.musixmatch.model.artist.Artist
import com.example.musixmatch.model.artist.BaseModel
import com.example.musixmatch.view.track.TrackFragment
import kotlinx.android.synthetic.main.fragment_artist.*
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
                Log.i("ratingFromRetrofit", ""+t.message.body.artist_list[0].artist.artist_rating)
                artistAdapterData(t)
            })


        viewModel.getArtistFromDB()
        val artistCakeInfoFromDB:MutableLiveData<List<Artist>>? = viewModel.artistFromDB()
        artistCakeInfoFromDB?.observe(this,object:Observer<List<Artist>>{
            override fun onChanged(t: List<Artist>?) {
                    Log.d("artistfromdb", t!![0].artist_country)
                Log.d("artistfromdb", t!![1].artist_country)
                Log.d("artistfromdb", t!![2].artist_country)
            }
        })

        viewModel.showProgress().observe(this,object:Observer<Boolean>{
            override fun onChanged(t: Boolean?) {
                if(t == true){
                    pgbar_artist.visibility = View.VISIBLE
                }
                if(t == false){
                    pgbar_artist.visibility = View.GONE
                }
            }

        })
    }

    private fun artistAdapterData(t: BaseModel){
        val adapter = ArtistAdapter(t,
            object : onClickArtistListener {
                override fun onClickedArtist(artist: Artist) {
                    val fragmentManager = activity?.supportFragmentManager
                    val transaction = fragmentManager?.beginTransaction()
                    val args = Bundle()
                    args.putString("artistName",artist.artist_name)
                    Log.d("firstFragmentartistname",artist.artist_name)
//                    args.putInt("artistId",artist.artist_id)
                    val trackFragment = TrackFragment()
                    trackFragment.arguments = args
                    transaction?.replace(R.id.fragmentContainer,trackFragment)

                        ?.addToBackStack(null)
                        ?.commit()
                }
            })
        recyclerview_artist.layoutManager = LinearLayoutManager(context)
        recyclerview_artist.adapter = adapter
    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }


}
