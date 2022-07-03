package com.example.citmoviedatabase.networking.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.citmoviedatabase.model.Movie.Movie

@Dao
interface FavoriteDAO {

    @Query("SELECT * FROM mylists WHERE favorite = :favorite")
    suspend fun getFavorites(favorite : Boolean = true) : List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFavorite(movie : Movie)
}