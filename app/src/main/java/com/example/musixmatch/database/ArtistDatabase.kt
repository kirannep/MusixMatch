package com.example.musixmatch.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.musixmatch.model.artist.Artist
import com.example.musixmatch.model.artist.Artist_list

@Database(entities = arrayOf(Artist::class,Artist_list::class), version = 1,exportSchema = false)
abstract class ArtistDatabase: RoomDatabase() {
    abstract fun artistDAO():ArtistSearchAccess

    companion object{
        @Volatile
        private var INSTANCE:ArtistDatabase? = null
        fun getInstance(context: Context): ArtistDatabase{
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    ArtistDatabase::class.java,
                    "artistdb"
                )
                    .build()
            }
            return INSTANCE as ArtistDatabase
        }
    }
}