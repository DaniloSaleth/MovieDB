package com.example.moviedb.di

import com.example.moviedb.infrastructure.api.MoshiHttp
import com.example.moviedb.infrastructure.api.create
import com.example.moviedb.network.MoviedbAPI
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

class MainModule {
    fun load() {
        loadKoinModules(module {
            loadApi()
            loadRepositories()
            loadViewModel()
        })
    }

    private fun Module.loadApi() {
        single<MoviedbAPI> { get<MoshiHttp>().create() }
    }

    private fun Module.loadRepositories() {

    }

    private fun Module.loadViewModel() {

    }
}