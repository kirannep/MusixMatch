package com.example.musixmatch.model.track

import com.google.gson.annotations.SerializedName


data class Body (

	@SerializedName("track_list") val track_list : List<Track_list>
)