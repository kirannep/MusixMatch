package com.example.musixmatch.model.track


import com.google.gson.annotations.SerializedName

data class Primary_genres (

	@SerializedName("music_genre_list") val music_genre_list : List<Music_genre_list>
)