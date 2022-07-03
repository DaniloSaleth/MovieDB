package com.example.citmoviedatabase.repository.RepositoryDB

import com.example.citmoviedatabase.model.Movie.Movie
import com.example.citmoviedatabase.networking.dataBase.FavoriteDAO

class RepositoryDBImpl(private val movieDAO: FavoriteDAO) : RepositoryDB{

    override suspend fun getFavorites(): RepositoryDBStatus {
        return try {
            RepositoryDBStatus.FavoritesSuccess(movieDAO.getFavorites())
        } catch (t : Throwable){
            RepositoryDBStatus.Error(t)
        }
    }

    override suspend fun setFavorite(movie: Movie): RepositoryDBStatus {
        return try {
            movie.favorite = true
            movieDAO.setFavorite(movie)
            RepositoryDBStatus.InsertListSuccess("You Insert ${movie.title} To Favorite List")
        } catch (t : Throwable){
            RepositoryDBStatus.Error(t)
        }
    }

    override suspend fun removeFavorite(movie: Movie): RepositoryDBStatus {
        return try {
            movie.favorite = false
            movieDAO.setFavorite(movie)
            RepositoryDBStatus.InsertListSuccess("You Remove ${movie.title} From Favorite List")
        } catch (t : Throwable){
            RepositoryDBStatus.Error(t)
        }
    }
}