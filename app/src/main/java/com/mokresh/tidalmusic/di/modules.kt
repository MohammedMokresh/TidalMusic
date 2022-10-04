package com.mokresh.tidalmusic.di


import com.mokresh.tidalmusic.albums.data.AlbumsViewModel
import com.mokresh.tidalmusic.api.ApiServices
import com.mokresh.tidalmusic.artist.data.ArtistsViewModel
import com.mokresh.tidalmusic.api.ListsRepository
import com.mokresh.tidalmusic.db.AppDatabase
import com.mokresh.tidalmusic.tracks.data.TracksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cacheModule by lazy {

    module {

        single {
            AppDatabase.getInstance(get()).favouriteAlbumsDAO()
        }
    }
}

val repositoryModule by lazy {
    module {
        single { ListsRepository(get()) }


    }
}


val viewModelModule by lazy {
    module {
        viewModel { ArtistsViewModel(get()) }
        viewModel { AlbumsViewModel(get(), get()) }
        viewModel { TracksViewModel(get()) }

    }
}


val serviceModule by lazy {
    module {
        single {
            ApiServices.create(get())
        }
    }

}
