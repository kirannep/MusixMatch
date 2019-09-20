package com.example.musixmatch.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.musixmatch.model.Artist
import com.example.musixmatch.model.BaseModel
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface ArtistSearchAccess {
    @Query("SELECT * FROM artistsearch")
    fun getArtist():Observable<List<Artist>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtist(insertArtist:Artist):Completable
}