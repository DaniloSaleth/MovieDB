package com.example.citmoviedatabase.di

import android.app.Application
import androidx.room.Room
import com.example.citmoviedatabase.Constants.BASE_URL
import com.example.citmoviedatabase.MyApp
import com.example.citmoviedatabase.networking.Endpoint
import com.example.citmoviedatabase.networking.dataBase.FavoriteDAO
import com.example.citmoviedatabase.networking.dataBase.MovieDB
import com.example.citmoviedatabase.repository.Repository
import com.example.citmoviedatabase.repository.RepositoryDB.RepositoryDB
import com.example.citmoviedatabase.repository.RepositoryDB.RepositoryDBImpl
import com.example.citmoviedatabase.repository.RepositoryImpl
import com.example.citmoviedatabase.ui.home.HomeViewModel
import com.example.citmoviedatabase.ui.movieDetail.DetailViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val favoritesDb = module {
    fun provideDatabase(application: Application): MovieDB {
        return Room.databaseBuilder(application, MovieDB::class.java, "MovieDB")
            .allowMainThreadQueries()
            .build()
    }

    fun provideDao(database: MovieDB): FavoriteDAO {
        return database.favoriteDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
}

val repository = module {
    single {
        RepositoryImpl(get()) as Repository
    }

    single {
        RepositoryDBImpl(get()) as RepositoryDB
    }
}

val viewModels = module {
    viewModel {
        HomeViewModel(get(),get())
    }
    viewModel {
        DetailViewModel(get())
    }

}

val serviceModule = module {
    single( named("BASE_URL")){
        BASE_URL
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(Endpoint::class.java)
    }
}