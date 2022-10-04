package com.mokresh.tidalmusic.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mokresh.tidalmusic.albums.model.AlbumsData

@Dao
interface FavouriteAlbumsDAO {
    @Query("SELECT * FROM album ORDER BY id DESC")
    fun getAllFavouritesAlbums(): List<AlbumsData>

    @Insert
    fun insertAlbumIntoFavourites(albumsData: AlbumsData)

    @Query("DELETE  FROM album WHERE id=:albumId")
    fun deleteAlbum(albumId: String?)

}