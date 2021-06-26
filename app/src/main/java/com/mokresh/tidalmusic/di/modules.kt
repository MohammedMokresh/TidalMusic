package com.mokresh.tidalmusic.di


import com.mokresh.tidalmusic.albums.AlbumsViewModel
import com.mokresh.tidalmusic.api.ApiServices
import com.mokresh.tidalmusic.artist.data.ArtistsViewModel
import com.mokresh.tidalmusic.artist.data.ListsRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val repositoryModule by lazy {
    module {
        single { ListsRepository(get()) }


    }
}


val viewModelModule by lazy {
    module {
        viewModel { ArtistsViewModel(get()) }
        viewModel { AlbumsViewModel(get()) }

    }
}


val serviceModule by lazy {
    module {
        single {
            ApiServices.create(get())
        }
    }

}
