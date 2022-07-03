package com.example.citmoviedatabase.repository.RepositoryDB


import com.example.citmoviedatabase.model.Movie.Movie

sealed class RepositoryDBStatus{
    data class FavoritesSuccess(val response: List<Movie>) : RepositoryDBStatus()
    data class InsertListSuccess(val response : String): RepositoryDBStatus()
    data class Error(val response: Throwable) : RepositoryDBStatus()
}

interface RepositoryDB {
    suspend fun getFavorites() : RepositoryDBStatus
    suspend fun setFavorite(movie: Movie) : RepositoryDBStatus
    suspend fun removeFavorite(movie: Movie) : RepositoryDBStatus
}