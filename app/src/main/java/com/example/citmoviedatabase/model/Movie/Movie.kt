package com.example.citmoviedatabase.model.Movie

import androidx.room.*
import com.example.citmoviedatabase.model.Genre.Genre
import com.example.citmoviedatabase.networking.dataBase.Converters

@Entity(tableName = "mylists")
class Movie(
    @PrimaryKey
    val id: Int,
    val overview : String?,
    val poster_path: String,
    val title: String?,
    val release_date: String?,
    @TypeConverters
    val genre_ids : List<Int>?,
    val vote_average: Double?,
    @TypeConverters
    val genres : List<Genre>?,
    val runtime : Int?,
    val backdrop_path :String?,
    var favorite : Boolean = false,
    var watched : Boolean = false,
    var to_watch : Boolean = false
    )