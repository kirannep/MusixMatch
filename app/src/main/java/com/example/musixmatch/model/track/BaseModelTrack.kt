package com.example.musixmatch.model.track

import com.google.gson.annotations.SerializedName

data class BaseModelTrack (

	@SerializedName("message") val message : Message
)