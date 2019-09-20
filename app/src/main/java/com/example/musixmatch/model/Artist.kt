/* 
Copyright (c) 2019 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */
package com.example.musixmatch.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "artistsearch")
data class Artist (

	@PrimaryKey @SerializedName("artist_id") val artist_id : Int,
	@SerializedName("artist_name") val artist_name : String,
//	@SerializedName("artist_name_translation_list") val artist_name_translation_list : Int,
	@SerializedName("artist_comment") val artist_comment : String,
	@SerializedName("artist_country") val artist_country : String,
//	@SerializedName("artist_alias_list") val artist_alias_list : List<Artist_alias_list>,
	@SerializedName("artist_rating") val artist_rating : Int,
	@SerializedName("artist_twitter_url") val artist_twitter_url : String,
//	@SerializedName("artist_credits") val artist_credits : Artist_credits,
	@SerializedName("restricted") val restricted : Int,
	@SerializedName("updated_time") val updated_time : String
)