package com.example.citmoviedatabase

import android.app.Application
import com.example.citmoviedatabase.di.favoritesDb
import com.example.citmoviedatabase.di.repository
import com.example.citmoviedatabase.di.serviceModule
import com.example.citmoviedatabase.di.viewModels
import com.example.citmoviedatabase.networking.dataBase.MovieDB
import com.example.citmoviedatabase.repository.RepositoryDB.RepositoryDB
import com.example.citmoviedatabase.repository.RepositoryDB.RepositoryDBImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            if(BuildConfig.DEBUG) {
                androidLogger()
            }
            androidContext(this@MyApp)
            modules(viewModels, repository, serviceModule,favoritesDb)
        }
    }
}