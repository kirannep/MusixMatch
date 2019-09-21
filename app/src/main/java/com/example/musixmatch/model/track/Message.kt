package com.example.musixmatch.model.track


import com.google.gson.annotations.SerializedName


data class Message (

	@SerializedName("header") val header : Header,
	@SerializedName("body") val body : Body
)