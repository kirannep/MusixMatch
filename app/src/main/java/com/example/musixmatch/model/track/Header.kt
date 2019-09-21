package com.example.musixmatch.model.track

import com.google.gson.annotations.SerializedName


data class Header (

	@SerializedName("status_code") val status_code : Int,
	@SerializedName("execute_time") val execute_time : Double,
	@SerializedName("available") val available : Int
)