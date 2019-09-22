package com.example.musixmatch.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

import com.example.musixmatch.R
import com.example.musixmatch.view.artist.ArtistFragment
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_search.setOnClickListener{
            gotoArtistFragment()
        }
    }

    fun gotoArtistFragment(){
        val fragmentManager = activity?.supportFragmentManager
        val transaction = fragmentManager?.beginTransaction()
        val args = Bundle()
        val searchArtist = et_search_artist.text
        args.putString("searchArtist",searchArtist.toString())
        val artistFragment = ArtistFragment()
        artistFragment.arguments = args
        transaction?.replace(R.id.fragmentContainer,artistFragment)
            ?.addToBackStack(null)
            ?.commit()
    }
}
