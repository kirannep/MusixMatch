package com.example.musixmatch.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musixmatch.model.artist.Artist_list
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface ArtistSearchAccess {
    @Query("SELECT * FROM artist_list")
    fun getArtist():Observable<List<Artist_list>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtist(insertArtist: List<Artist_list>):Completable
}