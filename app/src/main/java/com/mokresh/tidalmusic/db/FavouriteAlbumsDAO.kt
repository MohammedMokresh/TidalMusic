package com.mokresh.tidalmusic.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mokresh.tidalmusic.albums.model.AlbumsData

@Dao
interface FavouriteAlbumsDAO {
    @Query("SELECT * FROM album")
    fun getAllFavouritesAlbums(): List<AlbumsData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouriteAlbum(albums: AlbumsData)

    @Query("DELETE  FROM album WHERE id_album=:albumId")
    fun deleteAlbum(albumId: String?)

}