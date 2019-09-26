package com.example.musixmatch.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musixmatch.model.artist.Artist
import com.example.musixmatch.model.artist.Artist_list
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface ArtistSearchAccess {
    @Query("SELECT * FROM artistsearch where artist_name = :artist_name")
    fun getIndividualArtist(artist_name:String):Observable<List<Artist>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtist(insertArtist: List<Artist>):Completable

    @Query("SELECT * FROM artistsearch")
    fun getArtist():Observable<List<Artist>>
}