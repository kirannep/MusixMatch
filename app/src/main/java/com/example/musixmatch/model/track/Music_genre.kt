package com.example.musixmatch.model.track


import com.google.gson.annotations.SerializedName

data class Music_genre (

	@SerializedName("music_genre_id") val music_genre_id : Int,
	@SerializedName("music_genre_parent_id") val music_genre_parent_id : Int,
	@SerializedName("music_genre_name") val music_genre_name : String,
	@SerializedName("music_genre_name_extended") val music_genre_name_extended : String,
	@SerializedName("music_genre_vanity") val music_genre_vanity : String
)