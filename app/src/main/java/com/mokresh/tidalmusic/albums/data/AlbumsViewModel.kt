package com.mokresh.tidalmusic.albums.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mokresh.tidalmusic.albums.model.AlbumsData
import com.mokresh.tidalmusic.api.ListsRepository
import com.mokresh.tidalmusic.base.BaseViewModel
import com.mokresh.tidalmusic.db.FavouriteAlbumsDAO
import com.mokresh.tidalmusic.utils.UIEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AlbumsViewModel(
    private val listsRepository: ListsRepository,
    private val favouriteAlbumsDAO: FavouriteAlbumsDAO

) : BaseViewModel() {
    val favouriteAlbums = MutableLiveData<List<AlbumsData>>()

    fun getAlbums(query: String) {
        viewModelScope.launch {
            listsRepository.getAlbums(query).collectLatest {
                try {
                    publishUIEvent(UIEvent.RenderAlbumsList(it))
                } catch (ex: Exception) {
                    errorMessage.value = ex.message
                }
            }
        }
    }

    fun getFavourites() {
        viewModelScope.launch(Dispatchers.IO) {
            favouriteAlbums.postValue(favouriteAlbumsDAO.getAllFavouritesAlbums())
        }
    }

    fun insertFavourite(album: AlbumsData) {
        viewModelScope.launch(Dispatchers.IO) {
            favouriteAlbumsDAO.insertFavouriteAlbum(album)
        }
    }

    fun deleteFavourite(albumId: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            favouriteAlbumsDAO.deleteAlbum(albumId)
        }
    }
}