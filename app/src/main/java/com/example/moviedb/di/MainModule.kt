package com.example.moviedb.di

import com.example.moviedb.infrastructure.api.Constants.BASE_URL
import com.example.moviedb.navigation.MovieDetailsNavigation
import com.example.moviedb.navigation.MovieDetailsNavigationImpl
import com.example.moviedb.network.MoviedbAPI
import com.example.moviedb.repository.details.DetailsRepository
import com.example.moviedb.repository.details.DetailsRepositoryImpl
import com.example.moviedb.repository.home.HomeRepository
import com.example.moviedb.repository.home.HomeRepositoryImpl
import com.example.moviedb.ui.details.DetailsViewModel
import com.example.moviedb.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val loadRepositories = module {
    single { HomeRepositoryImpl(api = get()) as HomeRepository }
    single { DetailsRepositoryImpl(api = get()) as DetailsRepository }
}

val loadViewModels = module {
    viewModel { HomeViewModel(repository = get()) }
    viewModel { DetailsViewModel(repository = get()) }
}

val loadNavigation = module {
    factory<MovieDetailsNavigation> { MovieDetailsNavigationImpl() }
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