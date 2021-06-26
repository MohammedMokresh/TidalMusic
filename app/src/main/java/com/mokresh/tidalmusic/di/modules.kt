package com.mokresh.tidalmusic.di


import com.mokresh.tidalmusic.api.ApiServices
import com.mokresh.tidalmusic.artist.ArtistsViewModel
import com.mokresh.tidalmusic.artist.data.ArtistsRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val repositoryModule by lazy {
    module {
        single { ArtistsRepository(get()) }


    }
}


val viewModelModule by lazy {
    module {
        viewModel { ArtistsViewModel(get()) }

    }
}


val serviceModule by lazy {
    module {
        single {
            ApiServices.create(get())
        }
    }

}
