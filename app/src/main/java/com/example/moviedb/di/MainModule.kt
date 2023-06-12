package com.example.moviedb.di

import com.example.moviedb.infrastructure.api.Constants.BASE_URL
import com.example.moviedb.network.MoviedbAPI
import com.example.moviedb.repository.movie.MovieRepository
import com.example.moviedb.repository.movie.MovieRepositoryImpl
import com.example.moviedb.ui.home.HomeViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.converter.gson.GsonConverterFactory

val loadRepositories = module {
    single {
        MovieRepositoryImpl(
            api = get()
        ) as MovieRepository
    }
}

val loadViewModels = module {
    viewModel {
        HomeViewModel(
            repository = get()
        )
    }
}

val loadServices = module {
    single(named("BASE_URL")) {
        BASE_URL
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(MoviedbAPI::class.java)
    }
}