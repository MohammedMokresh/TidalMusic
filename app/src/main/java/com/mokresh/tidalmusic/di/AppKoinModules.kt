package com.mokresh.tidalmusic.di

import org.koin.core.module.Module

class AppKoinModules {

    companion object {
        fun getModules(): List<Module> {
            return mutableListOf(
                viewModelModule,
                repositoryModule,
                serviceModule
            )
        }
    }
}