package com.example.citmoviedatabase.networking.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.citmoviedatabase.model.Movie.Movie

const val DATABASE_VERSION = 1

@Database(
    entities = [Movie::class],
    version = DATABASE_VERSION
)
@TypeConverters(Converters::class)
abstract class MovieDB : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDAO
}