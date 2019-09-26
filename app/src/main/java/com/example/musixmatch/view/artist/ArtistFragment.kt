package com.example.musixmatch.view.artist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.musixmatch.R
import com.example.musixmatch.dependency_injection.component.DaggerAppComponent
import com.example.musixmatch.dependency_injection.network_module.NetworkModule
import com.example.musixmatch.model.artist.Artist
import com.example.musixmatch.model.artist.Artist_list
import com.example.musixmatch.model.artist.BaseModel
import com.example.musixmatch.model.artist.Body
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

        val artistFromBundle = arguments?.getString("searchArtist")

        DaggerAppComponent.builder()
            .networkModule(NetworkModule(activity!!.application))
            .build()
            .inject(this)




        viewModel = ViewModelProviders.of(this,fragmentArtistModelFactory).get(ArtistViewModel::class.java)
        if (artistFromBundle != null) {
            viewModel.getArtistFromRetrofit(artistFromBundle)
        }
//        FROM RETROFIT
        viewModel.artistRetrofit()?.observe(this,
            Observer<BaseModel> {
                    t ->
                Log.i("resultFromRetrofit", ""+t.message.body.artist_list[0].artist.artist_name)
                Log.i("ratingFromRetrofit", ""+t.message.body.artist_list[0].artist.artist_rating)
                    artistAdapterData(t.message.body.artist_list)
            })


        //FROM DB
//        if (artistFromBundle != null) {
//            viewModel.getIndividualArtistFromDB(artistFromBundle)
//        }
//        val individualArtistFromDB:MutableLiveData<List<Artist_list>>? = viewModel.individualArtistFromDB()
//        individualArtistFromDB?.observe(this,object:Observer<List<Artist_list>>{
//            override fun onChanged(t: List<Artist_list>?) {
//                Log.d("indiartistfromdb",""+t!![0].artist.artist_name)
//            }
//
//        })

        viewModel.getArtistFromDB()

        val artistInfoFromDB:MutableLiveData<List<Artist>>? = viewModel.artistFromDB()
        artistInfoFromDB?.observe(this,object:Observer<List<Artist>>{
            override fun onChanged(t: List<Artist>?) {
//                    Log.d("artistfromdb", t!![0].artist.artist_name)
                Log.d("artistfromdb", t!![0].artist_country)
                Log.d("artistfromDB",t!![0].artist_name)
                Log.d("artistratingDB",t!![0].artist_rating.toString())
                Log.d("artistfromdb", t!![2].artist_country)
//                artistAdapterData(t)
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

        viewModel.showSuccess.observe(this,object:Observer<Boolean>{
            override fun onChanged(t: Boolean?) {
                if( t == true){
                    Toast.makeText(context,"Successfully inserted",Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun artistAdapterData(t: List<Artist_list>){
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
