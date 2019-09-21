package com.example.musixmatch.model.track

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "trackTable")
data class Track (

	@PrimaryKey @SerializedName("track_id") val track_id : Int,
	@SerializedName("track_name") val track_name : String,
	@SerializedName("track_name_translation_list") val track_name_translation_list : List<String>,
	@SerializedName("track_rating") val track_rating : Int,
	@SerializedName("commontrack_id") val commontrack_id : Int,
	@SerializedName("instrumental") val instrumental : Int,
	@SerializedName("explicit") val explicit : Int,
	@SerializedName("has_lyrics") val has_lyrics : Int,
	@SerializedName("has_subtitles") val has_subtitles : Int,
	@SerializedName("has_richsync") val has_richsync : Int,
	@SerializedName("num_favourite") val num_favourite : Int,
	@SerializedName("album_id") val album_id : Int,
	@SerializedName("album_name") val album_name : String,
	@SerializedName("artist_id") val artist_id : Int,
	@SerializedName("artist_name") val artist_name : String,
	@SerializedName("track_share_url") val track_share_url : String,
	@SerializedName("track_edit_url") val track_edit_url : String,
	@SerializedName("restricted") val restricted : Int,
	@SerializedName("updated_time") val updated_time : String,
	@SerializedName("primary_genres") val primary_genres : Primary_genres
)